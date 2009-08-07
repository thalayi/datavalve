package org.jdataset.sample.wicket.custom;

import java.net.URL;

import org.apache.wicket.PageParameters;
import org.jdataset.ObjectDataset;
import org.jdataset.text.CommaDelimitedDataset;
import org.phonelist.model.Person;

public class FileCustomPaginatorPage extends AbstractCustomPaginatorPage {

	public FileCustomPaginatorPage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	public ObjectDataset<Person> createDataset() {

		URL url = getWicketApp().getClass().getResource("testData100.csv");
		
		String filename = url.getPath();
		CommaDelimitedDataset<Person> people = new CommaDelimitedDataset<Person>(
				filename) {

			@Override
			protected Person createObjectFromColumns(String[] columns) {
				return new Person(Long.valueOf(columns[0]), columns[1],
						columns[2], columns[3]);
			}

		};
		return people;
	}

}
