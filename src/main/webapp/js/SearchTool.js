var SearchTool = {
    /**
     * param = {}
     * @param formId 表单ID
     * @param updatePanelId 替换区域ID
     */
    search:function(param){
        var formId = param["formId"];
        var updatePanelId = param["updatePanelId"];
        var showResponse = function(data){
            $("#" + updatePanelId).html(data);
        };
        var options = {
           target: "#" + updatePanelId, //把服务器返回的内容放入id为output的元素中 
           //beforeSubmit: showRequest, //提交前的回调函数 
           success: showResponse,       //提交后的回调函数 
           //url: url,                  //默认是form的action， 如果申明，则会覆盖  
           //type: type,                //默认是form的method（get or post），如果申明，则会覆盖  
           //dataType: null,            //html(默认), xml, script, json...接受服务端返回的类型  
           //clearForm: true,           //成功提交后，清除所有表单元素的值  
           //resetForm: true,           //成功提交后，重置所有表单元素的值  
           timeout: 3000                //限制请求的时间，当请求大于3秒后，跳出请求  
        };
        $("#" + formId).ajaxSubmit(options);
    }
};