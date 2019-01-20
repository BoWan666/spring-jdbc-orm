package javaw.core.framework.jdbc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @Description:    动态数据源
* @Author:         wanbo
* @CreateDate:     2018/09/22 11:24
* @UpdateRemark:
* @Version:        1.0.0
*/
public class DynamicDataSource extends AbstractRoutingDataSource {  


    //entry的目的，主要是用来给每个数据源打个标记
	private DynamicDataSourceEntry dataSourceEntry;  
    
    @Override  
    protected Object determineCurrentLookupKey() {
        return this.dataSourceEntry.get();  
    }  
  
    public void setDataSourceEntry(DynamicDataSourceEntry dataSourceEntry) {  
        this.dataSourceEntry = dataSourceEntry;
    }
    
    public DynamicDataSourceEntry getDataSourceEntry(){
    		return this.dataSourceEntry;
    }
    
}
