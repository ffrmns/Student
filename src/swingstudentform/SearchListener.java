package swingstudentform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SearchListener implements ActionListener{
	
	private SearchFrame searchFrame;;
	private String chosenField;
	private String studentSearchString;
	
	public SearchListener(SearchFrame searchFrame) {
		this.searchFrame = searchFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.studentSearchString = searchFrame.getStudentSearch().getText();
		this.chosenField = searchFrame.getStudentButtonGroup().getSelection().getActionCommand();
		System.out.println("Search String: " + this.studentSearchString + "\nChosen field: " + this.chosenField);
		
		SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr").build();
		SolrQuery query;
		
		switch (chosenField) {
		case "Student Name":
			query = new SolrQuery("Fikri:" + studentSearchString);
			break;
		case "Student Year":
			query = new SolrQuery("2015:" + studentSearchString);
			break;
		case "Student ID":
			query = new SolrQuery("18115011:" + studentSearchString);
			break;
		default:
			query = null;
		}
		
				
		try {
			QueryResponse response = solrClient.query("Student", query);
	
			SolrDocumentList documentList = response.getResults();
			Iterator<SolrDocument> documentListIterator = documentList.iterator();
			while (documentListIterator.hasNext()) {
				SolrDocument singleDocument = documentListIterator.next();
				Collection<String> fieldNamesCollection = singleDocument.getFieldNames();
				for (String fieldName : fieldNamesCollection) {
					System.out.print(singleDocument.getFieldValue(fieldName) + " ");
				}
				System.out.println();
			}
			System.out.println(documentList.getNumFound());
		} catch (SolrServerException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
