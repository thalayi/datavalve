package org.jdataset.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.jdataset.AbstractQueryDataset;
import org.jdataset.Paginator;
import org.jdataset.Parameter;
import org.jdataset.QueryDataset;
import org.jdataset.db.RestrictionBuilder;
import org.jdataset.db.RestrictionBuilder.ParameterStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides the base implementation for a SQL based {@link QueryDataset} which
 * uses restrictions, parameters and ordering to define the final results.
 * 
 * @author Andy Gibson
 * 
 * @param <T>
 *            The type of object that will be returned in the dataset.
 */
public abstract class AbstractJdbcQueryDataset<T> extends AbstractQueryDataset<T>
		implements ResultSetObjectMapper<T> {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(AbstractJdbcQueryDataset.class);
	
	private transient Connection connection;
	private ResultSetObjectProcessor<T> resultSetObjectProcessor = new ResultSetObjectProcessor<T>();

	public AbstractJdbcQueryDataset() {
		this(null);
	}

	public AbstractJdbcQueryDataset(Connection connection) {
		this.connection = connection;
	}

	@Override
	protected List<T> fetchResultsFromDatabase(Paginator paginator,Integer count) {
		log.debug("FetchResultsFromDB, order = {}",paginator.getOrderKey());
		PreparedStatement statement = null;
		try {
			statement = buildPreparedStatement(getSelectStatement(),true,paginator);
			
			ResultSet resultSet = statement.executeQuery();

			List<T> results =  resultSetObjectProcessor.createListFromResultSet(resultSet, this,
					paginator.getFirstResult(), count);
			log.debug("Results processor returned {} results",results.size());
			return results;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	protected Integer fetchResultCount() {
		PreparedStatement statement = null;
		try {
			statement = buildPreparedStatement(getCountStatement(),false,null);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
			int value = rs.getInt(1);
				log.debug("Fetch Result Count SQL returned {}",value);
				return value;
			} else {
				log.error("Fetch Result Count SQL returned no rows!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	private PreparedStatement buildPreparedStatement(String selectSql,boolean includeOrderBy,Paginator paginator)
			throws SQLException {
		
		RestrictionBuilder rb = new RestrictionBuilder(this,
				ParameterStyle.ORDERED_QUESTION_MARKS);
		String sql = selectSql + rb.buildWhereClause();
		if (includeOrderBy) {
			sql = sql + calculateOrderByClause(paginator);
		}
		log.debug("Building statement as : {}",sql);
		PreparedStatement statement = connection.prepareStatement(sql);
		List<Parameter> params = rb.getParameterList();
		for (int i = 0;i < params.size();i++) {
			Parameter param = params.get(i);			
			log.debug("Setting parameter {} to '{}'",Integer.valueOf(i),param.getValue());
			statement.setObject(i+1, param.getValue());
		}
		
		return statement;
	}

	public abstract T createObjectFromResultSet(ResultSet resultSet)
			throws SQLException;
}
