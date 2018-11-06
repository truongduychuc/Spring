package SpringMVCWeb.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;

@SuppressWarnings("all")
public class PaginationResult<E> {   //this class help to show product pages
	private int totalRecords; //amount of records
	private int currentPage;	
	private List<E> list; // this is used to contain list of productInfo query records from productDAOImpl.queryProducts 
	private int maxResult;
	private int totalPages;  //amount of pages
	
	private int maxNavigationPage;
	private List<Integer> navigationPages;  //list of pages (if product list has many pages)
	public PaginationResult(Query query,int page,int maxResult,int maxNavigationPage) {
		final int pageIndex=page-1<0?0:page-1; //page index
		int fromRecordIndex=pageIndex*maxResult;
		int maxRecordeIndex=fromRecordIndex+maxResult;
		ScrollableResults resultScroll=query.scroll(ScrollMode.SCROLL_INSENSITIVE);
		List results=new ArrayList();  //include products
		boolean hasResult=resultScroll.first(); //has any record
		if(hasResult) {   //if has any record
			//slide to 
			hasResult=resultScroll.scroll(fromRecordIndex);
			if(hasResult) {
				do {
					E record=(E)resultScroll.get(0);
					results.add(record);
				}while(resultScroll.next()&&resultScroll.getRowNumber()>=fromRecordIndex&&resultScroll.getRowNumber()<maxRecordeIndex);
			}
			resultScroll.last();
		}
		this.totalRecords=resultScroll.getRowNumber()+1;
		this.currentPage=pageIndex+1;
		this.list=results;
		this.maxResult=maxResult;
		
		this.totalPages=(this.totalRecords)/this.maxResult+1;
		this.maxNavigationPage=maxNavigationPage;
		if(maxNavigationPage<totalPages) {
			this.maxNavigationPage=maxNavigationPage;
		}
		this.calcNavigationPages();
		
	}
	private void calcNavigationPages() {
		this.navigationPages=new ArrayList<>();
		int current=this.currentPage>this.totalPages?this.totalPages:this.currentPage;
		int begin=current-this.maxNavigationPage/2;
		int end=current+this.maxNavigationPage/2;
		//first page
		navigationPages.add(1);
		if(begin>2) {
			//using for '...'
			navigationPages.add(-1);
		}
		for(int i=begin;i<end;i++) {
			if(i>1&&i<this.totalPages) {
				navigationPages.add(i);
			}
		}
		if(end<this.totalPages-2) {
			//using for '...'
			navigationPages.add(-1);
		}
		//last page
		navigationPages.add(this.totalPages);
	}
	public int getMaxResult() {
		return maxResult;
	}
	
	public int getTotalRecords() {
		return totalRecords;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<E> getList() {
		return list;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
	
}
