/*
 * Copyright 2010, Andrew M Gibson
 *
 * www.andygibson.net
 *
 * This file is part of DataValve.
 *
 * DataValve is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DataValve is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with DataValve.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.fluttercode.datavalve.samples.wicketdemo.model;

import javax.persistence.EntityManager;

import org.fluttercode.datafactory.impl.DataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates the test data
 * 
 * @author Andy Gibson
 * 
 */
public class ModelBuilder {

	private static Logger log = LoggerFactory.getLogger(ModelBuilder.class);
	private final static DataFactory dataFactory = new DataFactory();

	public static void execute(EntityManager em, int count) {

		log.debug("Building model database with {} rows", count);
		em.getTransaction().begin();
		try {
			for (int i = 0; i < count; i++) {
				Person person = new Person();
				person.setFirstName(dataFactory.getFirstName());
				person.setLastName(dataFactory.getLastName());
				person.setPhone(dataFactory.getNumberText(10));
				em.persist(person);
				// commit every 20 rows
				if (i % 20 == 0) {
					em.getTransaction().commit();
					em.getTransaction().begin();
				}
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

}
