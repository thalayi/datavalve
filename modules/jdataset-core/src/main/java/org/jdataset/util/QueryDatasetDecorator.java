package org.jdataset.util;

import java.util.List;
import java.util.Map;

import org.jdataset.ObjectDataset;
import org.jdataset.QueryDataset;

/**
 * Decorator class that can be used to decorate an existing
 * {@link QueryDataset}.
 * 
 * @author Andy Gibson
 * 
 * @param <T> Type of object this dataset contains
 */
public class QueryDatasetDecorator<T> extends ParameterizedDatasetDecorator<T> implements QueryDataset<T> {

	private static final long serialVersionUID = 1L;
	
	private QueryDataset<T> dataset;
	
	public QueryDatasetDecorator(QueryDataset<T> dataset) {
		super(dataset);
		this.dataset = dataset;
	}
	
	public String getCountStatement() {
		return dataset.getCountStatement();
	}

	public Map<String, String> getOrderKeyMap() {
		return dataset.getOrderKeyMap();
	}

	public List<String> getRestrictions() {
		return dataset.getRestrictions();
	}

	public String getSelectStatement() {
		return dataset.getSelectStatement();
	}

	public void setCountStatement(String countStatement) {		
		dataset.setCountStatement(countStatement);
	}

	public void setOrderKeyMap(Map<String, String> orderKeyMap) {
		dataset.setOrderKeyMap(orderKeyMap);
	}

	public void setRestrictions(List<String> restrictions) {
		dataset.setRestrictions(restrictions);	
	}

	public void setSelectStatement(String selectStatement) {
		dataset.setSelectStatement(selectStatement);
	}

	public void addRestriction(String restriction) {
		dataset.addRestriction(restriction);		
	}
}
