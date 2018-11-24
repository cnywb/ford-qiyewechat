/**
 * 固特异Js工具类
 * 
 * **/
var gtyutil = {
	
		//获取图文的html
		getGraphicHtml : function(id,wc,callback){
			//加载图文信息
			var url = hhutil.getRootPath()+"/wxset!findDTImgTextJsonList.action";
			if(!hhutil.isEmpty(id)){
				url += "?imgtextid="+id ;
			}
			hhutil.ajaxcallback(url, null, callback,function(data,callback){
				var dataList = data.data ;
				//遍历生成图文
				var html = "";
				if(null!=dataList){
					for(var x=0,len=dataList.length;x<len;x++){
						var graphic = dataList[x];
						if(!hhutil.isEmpty(graphic) && !hhutil.isEmpty(graphic.imgtextid)){
							var i = graphic.imgtextid ;
							if(null!=graphic.imgtexttype && graphic.imgtexttype==1){
								//单图文
								var item = graphic.childList[0] ;
								if(!hhutil.isEmpty(item)){
									if (!hhutil.isEmpty(wc) && wc.assetId != "s_" + i){
										html += "<div class='item itemSingle' data-id='s_" + i + "'>";
										html += "<div class='mask'></div>";
									} else {
										html += "<div class='item itemSingle current' data-id='s_" + i + "'>";
										html += "<div class='mask' style='display:block'></div>";
									}
									html += "<h2><a target='_blank'  href='"+hhutil.getRootPath()+"/wxset!showDTImgText.action?imgtextlistid="+item.imgtextlistid+"'>"+hhutil.getValue(item.title)+"</a></h2>";
									html += "<span class='date'>"+hhutil.parseMonthDate(graphic.createtime)+"</span>";
									html += "<div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.imgurl)+"'></div>";
									html += "<p>"+hhutil.getValue(item.summary)+"</p></div>";
								}
							}else if(graphic.imgtexttype==2){
								//多图文
								if (!hhutil.isEmpty(wc) && wc.assetId != "m_" + i){
									html += "<div class='item itemMulit' data-id='m_" + i + "'>";
									html += "<div class='mask'></div>";
								} else {
									html += "<div class='item itemMulit current' data-id='m_" + i + "'>";
									html += "<div class='mask' style='display:block'></div>";
								}
								html += "<span class='date'>"+hhutil.parseMonthDate(graphic.createtime)+"</span>";
								var itemList = graphic.childList;
								if(null!=itemList && itemList.length>0){
									for(var j=0,lenj=itemList.length;j<lenj;j++){
										var item = itemList[j];
										if(!hhutil.isEmpty(item)){
											if(j==0){
												html += "<div class='cover'><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.imgurl)+"' /><p><a target='_blank'  href='"+hhutil.getRootPath()+"/wxset!showDTImgText.action?imgtextlistid="+item.imgtextlistid+"'>"+hhutil.getValue(item.title)+"</a></p></div></div>";
											}else{
												html += "<div class='sub clearFix'><p><a target='_blank' href='"+hhutil.getRootPath()+"/wxset!showDTImgText.action?imgtextlistid="+item.imgtextlistid+"'>"+hhutil.getValue(item.title)+"</a></p><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.imgurl)+"'/></div></div>";
											}
										}
									}
								}
								html+="</div>";
								//html += "<div class='sub clearFix'><p><a href='#'>图片的描述文字图片的描述文字图片的描述文字</a></p><div class='img'><img src='"+hhutil.getRootPath()+"/theme/grey/images/0.jpeg' /></div></div></div>";
							}
							//for (var i = 0; i < 10; i++){
						//}
						}
						//============
					}
				}
				//==========
				callback(html);
				//==========
			});
		
		},
		
		
		//获取图文的html
		getGraphicHtmlMenu : function(obj,callback){
			//加载图文信息
			var url = hhutil.getRootPath()+"/wxset!findDTImgTextJsonList.action";
			if(!hhutil.isEmpty(obj.id)){
				url += "?imgtextid="+obj.id ;
			}
			obj.callback = callback ;
			hhutil.ajaxcallback(url, null, obj,function(data,obj){
				var callback = obj.callback;
				var dataList = data.data ;
				//遍历生成图文
				var html = "";
				if(null!=dataList){
					for(var x=0,len=dataList.length;x<len;x++){
						var graphic = dataList[x];
						if(!hhutil.isEmpty(graphic) && !hhutil.isEmpty(graphic.imgtextid)){
							var i = graphic.imgtextid ;
							if(null!=graphic.imgtexttype && graphic.imgtexttype==1){
								//单图文
								var item = graphic.childList[0] ;
								if(!hhutil.isEmpty(item)){
									//if (!hhutil.isEmpty(wc) && wc.assetId != "s_" + i){
										html += "<div class='item itemSingle' data-id='s_" + i + "'>";
										html += "<div class='mask'></div>";
									//} else {
									//	html += "<div class='item itemSingle current' data-id='s_" + i + "'>";
									//	html += "<div class='mask' style='display:block'></div>";
									//}
									html += "<h2><a target='_blank' href='"+hhutil.getRootPath()+"/wxset!showDTImgText.action?imgtextlistid="+item.imgtextlistid+"'>"+hhutil.getValue(item.title)+"</a></h2>";
									html += "<span class='date'>"+hhutil.parseMonthDate(graphic.createtime)+"</span>";
									html += "<div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.imgurl)+"'></div>";
									html += "<p>"+hhutil.getValue(item.summary)+"</p></div>";
								}
							}else if(graphic.imgtexttype==2){
								//多图文
								//if (!hhutil.isEmpty(wc) && wc.assetId != "m_" + i){
									html += "<div class='item itemMulit' data-id='m_" + i + "'>";
									html += "<div class='mask'></div>";
								//} else {
								//	html += "<div class='item itemMulit current' data-id='m_" + i + "'>";
								//	html += "<div class='mask' style='display:block'></div>";
								//}
								html += "<span class='date'>"+hhutil.parseMonthDate(graphic.createtime)+"</span>";
								var itemList = graphic.childList;
								if(null!=itemList && itemList.length>0){
									for(var j=0,lenj=itemList.length;j<lenj;j++){
										var item = itemList[j];
										if(!hhutil.isEmpty(item)){
											if(j==0){
												html += "<div class='cover'><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.imgurl)+"' /><p><a target='_blank' href='"+hhutil.getRootPath()+"/wxset!showDTImgText.action?imgtextlistid="+item.imgtextlistid+"'>"+hhutil.getValue(item.title)+"</a></p></div></div>";
											}else{
												html += "<div class='sub clearFix'><p><a target='_blank' href='"+hhutil.getRootPath()+"/wxset!showDTImgText.action?imgtextlistid="+item.imgtextlistid+"'>"+hhutil.getValue(item.title)+"</a></p><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.imgurl)+"'/></div></div>";
											}
										}
									}
								}
								html+="</div>";
								//html += "<div class='sub clearFix'><p><a href='#'>图片的描述文字图片的描述文字图片的描述文字</a></p><div class='img'><img src='"+hhutil.getRootPath()+"/theme/grey/images/0.jpeg' /></div></div></div>";
							}
							//for (var i = 0; i < 10; i++){
						//}
						}
						//============
					}
				}
				//==========
				callback(html,obj);
				//==========
			});
		
		},
		
		getPhotoHtml : function (id,wc,callback){
			//加载素材图片
			var url = hhutil.getRootPath()+"/wxset!findDTImgJsonList.action";
			if(!hhutil.isEmpty(id)){
				url+="?imgid="+id ;
			}
			
			hhutil.ajaxcallback(url, null,callback, function(data,callback){
				var dataList = data.data ;
				//遍历生成素材图片
				var html = "";
				if(null!=dataList){
					for(var x=0,len=dataList.length;x<len;x++){
						var item = dataList[x];
						var i = item.imgid ;
						if(!hhutil.isEmpty(item)){
							if (!hhutil.isEmpty(wc) && wc.picId != "p_" + i){
								html += "<div class='item' data-id='p_" + i + "'><div class='mask'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"'/></div></div>";
							} else {
								html += "<div class='item current' data-id='p_" + i + "'><div class='mask' style='display:block'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"' /></div></div>";
							}
						}
						//============
					}
				}
				callback(html);
			});	
		},
		
		getPhotoHtmlMenu : function (obj,callback){
			//加载素材图片
			var url = hhutil.getRootPath()+"/wxset!findDTImgJsonList.action";
			if(!hhutil.isEmpty(obj.id)){
				url+="?imgid="+obj.id ;
			}
			obj.callback = callback ;
			hhutil.ajaxcallback(url, null,obj, function(data,obj){
				var callback = obj.callback ;
				var dataList = data.data ;
				//遍历生成素材图片
				var html = "";
				if(null!=dataList){
					for(var x=0,len=dataList.length;x<len;x++){
						var item = dataList[x];
						var i = item.imgid ;
						if(!hhutil.isEmpty(item)){
							//if (!hhutil.isEmpty(wc) && wc.picId != "p_" + i){
							html += "<div class='item' data-id='p_" + i + "'><div class='mask'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"'/></div></div>";
							//} else {
							//	html += "<div class='item current' data-id='p_" + i + "'><div class='mask' style='display:block'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"' /></div></div>";
							//}
						}
						//============
					}
				}
				callback(html,obj);
			});	
		},
		
		// 加载语音
		getAudioHtml : function (id,wc,callback){
			//加载素材图片
			var url = hhutil.getRootPath()+"/wxset!findDTAudioJsonList.action";
			if(!hhutil.isEmpty(id)){
				url+="?id="+id ;
			}
			
			hhutil.ajaxcallback(url, null,callback, function(data,callback){
				var dataList = data.data ;
				//遍历生成语音素材
				var html = "";
				if(null!=dataList){
					for(var x=0,len=dataList.length;x<len;x++){
						var item = dataList[x];
						var i = item.id ;
						if(!hhutil.isEmpty(item)){
							if (!hhutil.isEmpty(wc) && wc.audioId != "a_" + i){
								html += "<div class='item' data-id='a_" + i + "' data-audio='"+hhutil.getRootPath()+item.path+"'><div class='mask'></div>";
								//html += "<div class='item' data-id='a_" + i + "'><div class='mask'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"'/></div></div>";
							} else {
							html += "<div class='item current' data-id='a_" + i + "' data-audio='"+hhutil.getRootPath()+item.path+"'><div class='mask' style='display:block'></div>";
								//html += "<div class='item current' data-id='a_" + i + "'><div class='mask' style='display:block'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"' /></div></div>";
							}
							html += "<a href='javascript:void(0)' class='play'><sub></sub><span>"+item.time+"s</span></a>";
							html += "<div class='info'><h1>"+item.name+"</h1><span>"+parseInt(parseInt(item.size)/1024)+"K</span></div></div>";
							/**
							if (wc.audioId != "a_" + i){
								html += "<div class='item' data-id='a_" + i + "' data-audio='http://192.168.0.16/php/uploads/14200079099236.mp3'><div class='mask'></div>";
							} else {
								html += "<div class='item current' data-id='a_" + i + "' data-audio='http://192.168.0.16/php/uploads/14200079099236.mp3'><div class='mask' style='display:block'></div>";
							}**/
							
							
						}
						//============
					}
				}
				callback(html);
			});	
		},
		
		getAudioHtmlMenu : function (obj,callback){
			//加载素材图片
			var url = hhutil.getRootPath()+"/wxset!findDTAudioJsonList.action";
			if(!hhutil.isEmpty(obj.id)){
				url+="?imgid="+obj.id ;
			}
			obj.callback = callback ;
			hhutil.ajaxcallback(url, null,obj, function(data,obj){
				var callback = obj.callback ;
				var dataList = data.data ;
				//遍历生成素材图片
				var html = "";
				if(null!=dataList){
					for(var x=0,len=dataList.length;x<len;x++){
						var item = dataList[x];
						var i = item.id ;
						if(!hhutil.isEmpty(item)){
							//if (!hhutil.isEmpty(wc) && wc.picId != "p_" + i){
							//html += "<div class='item' data-id='a_" + i + "'><div class='mask'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"'/></div></div>";
							//} else {
							//	html += "<div class='item current' data-id='p_" + i + "'><div class='mask' style='display:block'></div><div class='img'><img src='"+hhutil.getRootPath()+hhutil.getValue(item.img)+"' /></div></div>";
							//}
						}
						//============
					}
				}
				callback(html,obj);
			});	
		}
		
		
};