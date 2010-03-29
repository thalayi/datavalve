/*
* Copyright 2010, Andrew M Gibson
*
* www.andygibson.net
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.fluttercode.spigot.samples.wicketdemo.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.wicket.PageParameters;
import org.fluttercode.spigot.dataset.ParameterizedDataset;
import org.fluttercode.spigot.impl.provider.jdbc.AbstractJdbcDataProvider;
import org.fluttercode.spigot.provider.QueryDataProvider;
import org.fluttercode.spigot.samples.wicketdemo.model.Person;

/**
 * @author Andy Gibson
 * 
 */
public class SqlSearchPage extends AbstractSearchPage {

	public SqlSearchPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	public ParameterizedDataset<Person> createDataset() {
		Connection connection = getWicketApp().getConnection();
		QueryDataProvider<Person> provider = new AbstractJdbcDataProvider<Person>(
				connection) {

			@Override
			public Person createObjectFromResultSet(ResultSet resultSet)
					throws SQLException {
				Person person = new Person();
				person.setId(resultSet.getLong(1));
				person.setFirstName(resultSet.getString(2));
				person.setLastName(resultSet.getString(3));
				person.setPhone(resultSet.getString(4));
				return person;
			}
		};
		
		provider.setCountStatement("select count(1) from PERSONS p");
		provider.setSelectStatement("select * from PERSONS p");
		provider.addRestriction(
				"upper(p.first_Name) like upper(:firstNameValue)");
		provider.addRestriction(
				"upper(p.last_Name) like upper(:lastNameValue)");
		provider.addRestriction("p.phone like :phoneValue");
		provider.addRestriction("p.id = :id");
		provider.getOrderKeyMap().put("id", "p.id");
		provider.getOrderKeyMap().put("name", "p.last_Name,p.first_Name");
		return new ParameterizedDataset<Person>(provider);
	}

}