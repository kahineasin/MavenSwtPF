package pf.java.pfHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;

import pf.java.pfHelper.config.PFDataHelper;

public class PagingParameters {

    private int _pageIndex = 0;
	private Integer _pageSize = null;
	private Map<String,String> request=null;
	private String _filterValue=null;
	private String _sort=null;

    //public Integer PageIndex { get { return _pageIndex; } set { if (value.HasValue) { _pageIndex = value.Value; } } }//从0开始
    //public Integer PageSize { get { return _pageSize; } set { if (value.HasValue) { _pageSize = value.Value; } } }
    //public Integer PageSize { get { return _pageSize; } set { _pageSize = value; } }
    //public Integer PageStart { get { return PageIndex == null || PageSize == null ? null : PageIndex * PageSize; } }//从0开始
    //public Integer PageEnd { get { return PageStart == null ? null : PageStart + PageSize - 1; } }//这个值有可能超出Table的Rows索引

    //public String Sort { get; set; }
    /// <summary>
    /// 模糊查找
    /// </summary>
    //public String FilterValue { get; set; }

    ///// <summary>
    ///// 是否导出(除了sql分页的,都导出全部)(其实,sql分页的没必要导出了)
    ///// </summary>
    //public bool IsExport { get; set; }
    /// <summary>
    /// 显示所有用户可见列(因为增加功能用户可设置可见列之后,设置窗口的调用方法需显示全部列)(可空是因为前端不提交绑定会失败)
    /// </summary>
    public Boolean ShowAllColumn = false;
    public int getPageIndex() {
		return _pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		if (pageIndex!=null) { _pageIndex = pageIndex; }
	}

    public Integer getPageSize() {
		return _pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this._pageSize = pageSize;
	}
	 public Integer getPageStart() {
			return //_pageIndex == null ||
					_pageSize == null ? null : _pageIndex * _pageSize;
		}
	 public Integer getPageEnd() {
		   Integer pageStart=getPageStart() ;
			return  pageStart == null ? null : pageStart + _pageSize - 1;
		}


	    public String GetRequest(String name) {
            String field = "";
            if (name.indexOf(".") >= 0) field = name.split(".")[1];
            return request.containsKey(field) ? request.get(field):request.get(name);
	    } 
	    public void SetRequest(String name,String value) {
            if (name.indexOf(".") >= 0) { name = name.split(".")[1]; }


//            int ti = 0;
            PFRef<Integer> ti = new PFRef<Integer>(0);
            String lowerName = name.toLowerCase();
            if (lowerName == "pageindex" && PFDataHelper.TryParseInt(value,  ti))
            {
                _pageIndex = ti.GetValue();
            }
            else
            if (lowerName == "pagesize" && PFDataHelper.TryParseInt(value,  ti))
            {
            	_pageIndex = ti.GetValue();
            }
            else
            if (lowerName == "sort")
            {
            	_sort = value;
            }
            else
            if (lowerName == "filtervalue")
            {
            	_filterValue = value;
            }
            if (request == null) { request = new HashMap<String,String>(); }
            request.replace(name, value);
	    } 
//    public String this[String name]
//    {
//        get
//        {
//            var field = String.Empty;
//            if (name.IndexOf(".") >= 0) field = name.Split('.')[1];
//            return request[field] ?? request[name]// ?? request[getAliasName(name)]
//                ;
//        }
//        set
//        {
//            if (name.IndexOf(".") >= 0) { name = name.Split('.')[1]; }
//
//
//            int ti = 0;
//            var lowerName = name.ToLower();
//            if (lowerName == "pageindex" && int.TryParse(value, out ti))
//            {
//                PageIndex = ti;
//            }
//            else
//            if (lowerName == "pagesize" && int.TryParse(value, out ti))
//            {
//                PageSize = ti;
//            }
//            else
//            if (lowerName == "sort")
//            {
//                Sort = value;
//            }
//            else
//            if (lowerName == "filtervalue")
//            {
//                FilterValue = value;
//            }
//            if (request == null) { request = new NameValueCollection(); }
//            request[name] = value;
//        }
//    }
    public PagingParameters(Map<String,String> query)
    {
        this.SetRequestData(query);
    }

    public PagingParameters()
    {
        this.SetRequestData(new HashMap<String,String>());
    }

    public PagingParameters SetRequestData(Map<String,String> values)
    {
    	Iterator<String> iter = values.keySet().iterator();
    	  while(iter.hasNext()){
    	   String key=iter.next();
    	   String value = values.get(key);
    	   request.replace(key, value);
    	  }
        return this;
    }
    public PagingParameters SetRequestData(JsonNode values)
    {

        if (values.isObject())
        {
            Iterator<Entry<String, JsonNode>> it = values.fields();
            while (it.hasNext())
            {
                Entry<String, JsonNode> entry = it.next();
          		String key=entry.getKey();
          		String value =entry.getValue().asText();
          		request.replace(key, value);
            }
        }
        
        return this;
    }

//    public PagingParameters SetRequestData(JToken values)
//    {
//        if (values != null)
//        {
//            foreach (JProperty item in values.Children())
//            {
//                if (item != null) {
//                    this[item.Name] = item.Value.ToString();
//                }
//            }
//        }
//        return this;
//    }
}
