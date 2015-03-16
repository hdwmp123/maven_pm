var SelectTool={
        /**
         * param = {}
         * @param method 加载方法 get|post
         * @param elId  select 对象id
         * @param haveAll 是否有显示"<全部>"
         * @param url 请求数据的url
         * @param postData 请求参数
         * @param async 是否异步
         * @param valueName value名称(value)
         * @param textName text名称(text)
         * @param defaultValue 默认值
         * @param callback 回调函数
         */
        initSelectData:function(param){
            var method = param["method"];
            var elId = param["elId"];
            var haveAll = param["haveAll"];
            var url = param["url"];
            var postData = param["postData"];
            var async = param["async"];
            var valueName = param["valueName"];
            var textName = param["textName"];
            var defaultValue = param["defaultValue"];
            var callback = param["callback"];
            //默认值设置
            if(!method){method = "get";}
            if(!haveAll){haveAll = false;}
            if(!url){
                console.log("url必填项");
                return;
            }
            if(!postData){postData = {};}
            if(!async){async = true;}
            if(!valueName){valueName = "id";}
            if(!textName){textName = "name";}
            console.log(param);
            console.log(defaultValue === null);
            if(!defaultValue || defaultValue ===null){defaultValue = "";}
            
            //
            var dataToSelect=[];
            var success = function(data) {
                try{
                    if(data!=null&&data.length>0){
                        for (var i = 0; i < data.length; i++) {
                            var dataTemp = {"text":data[i][textName],"value":data[i][valueName]};
                            dataToSelect[i] = dataTemp;
                        }
                    }
                    SelectTool.dataToSelect({
                        data:data,
                        dataToSelect:dataToSelect,
                        elId:elId,
                        haveAll:haveAll,
                        defaultValue:defaultValue,
                        callback:callback
                    });
                }catch(e){
                    alert("SelectTool.loadPost():"+e);
                }
            };
            var error = function (xhr) {
                var errorMessage = eval("("+xhr.responseText+")");
                showErrorDialog("error", errorMessage[0].message);
            };
            jQuery.ajax({
                type: method,
                url: url,
                data: postData,
                success: success,
                error:error,
                async:async
            });
        },
        /**
         * param = {}
         * @param data :原始请求响应数据
         * @param dataToSelect :数据=[{"value":"value1":"text":"text1"},{"value":"value2":"text":"text2"}];
         * @param elId select 对象id
         * @param haveAll 是否有显示"<全部>"
         * @param defaultValue 默认值
         */
        dataToSelect:function(param){
            var data = param["data"];
            var dataToSelect = param["dataToSelect"];
            var elId = param["elId"];
            var haveAll = param["haveAll"];
            var callback = param["callback"];
            var defaultValue = param["defaultValue"];
            try{
                
                elId = "#"+elId;
                var selectEl = jQuery(elId)[0];
                selectEl.options.length = 0;
                
                if(haveAll){
                    selectEl.options.add(new Option("全部",""));
                }
                
                var option = null;
                var txt = null;
                var value = null;
                for(var i=0;i<dataToSelect.length;i++){
                    txt = dataToSelect[i].text;
                    value = dataToSelect[i].value;
                    option = new Option(txt,value);
                    if(value==defaultValue){
                        option.selected = "selected";
                    }
                    selectEl.options.add(option);
                }
                
                if(callback){
                    callback(data);
                }
            }catch(e){
            }
        }
};