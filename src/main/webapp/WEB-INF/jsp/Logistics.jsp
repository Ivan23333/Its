<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="script.html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=m0mhkoxTVrCGRBNARuoN46q8jrXkE87b"></script>
	<title>设置驾车路线途经点</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">

	var map = new BMap.Map("allmap");
	
	var warehouse_deliver_lng=parseFloat("${logistics.warehouse_deliver_lng}");
	var warehouse_deliver_lat=parseFloat("${logistics.warehouse_deliver_lat}");
	
 	var warehouse_receiver_lng=parseFloat("${logistics.warehouse_receiver_lng}");
	var warehouse_receiver_lat=parseFloat("${logistics.warehouse_receiver_lat}");
	
	var add="${logistics.detail_address}";
	var city="${order.city}";
	var status = parseInt("${goods.status}"); 
	
	
	var p1 = new BMap.Point(warehouse_deliver_lng,warehouse_deliver_lat);
	var p2 = new BMap.Point(warehouse_receiver_lng,warehouse_receiver_lat);
	var p3 = new BMap.Point(113.668965,34.787904);
	
	map.centerAndZoom(p1, 9);
	map.enableScrollWheelZoom(true);
	
	var myGeo = new BMap.Geocoder();
	
	myGeo.getPoint(add, function(p3){
		if (p3) {
			var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
			driving.search(p1, p3,{waypoints:[p2]});  
			
			if(status == 1){
				
				
				var	output="备货中：";
				output +="\n"; 
				output +="商家正在备货中，请您耐心等待。"; 

				var opts = {
				  position : p1,    // 指定文本标注所在的地理位置
				  offset   : new BMap.Size(30, -30)    //设置文本偏移量
				}
				var label = new BMap.Label(output, opts);  // 创建文本标注对象
					label.setStyle({
						 color : "black",
						 fontSize : "12px",
						 height : "20px",
						 lineHeight : "20px",
						 fontFamily:"微软雅黑"
					 });
				map.addOverlay(label);   
				
				var marker = new BMap.Marker(p1);  // 创建标注
				map.addOverlay(marker);               // 将标注添加到地图中
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

			}else if(status == 2){
				
				var	output="已发货：";
				output +="\n"; 
				output +="商家已发货，您的货物正在前往您所在地市。"; 

				var opts = {
				  position : p1,    // 指定文本标注所在的地理位置
				  offset   : new BMap.Size(30, -30)    //设置文本偏移量
				}
				var label = new BMap.Label(output, opts);  // 创建文本标注对象
					label.setStyle({
						 color : "black",
						 fontSize : "12px",
						 height : "20px",
						 lineHeight : "20px",
						 fontFamily:"微软雅黑"
					 });
				map.addOverlay(label); 
				var marker = new BMap.Marker(p1);  // 创建标注
				map.addOverlay(marker);               // 将标注添加到地图中
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				
				
			}else if(status == 3){
				
				var	output="已到库：";
				output +="\n"; 
				output +="您的货物已经到达您所在地市。"; 

				var opts = {
				  position : p2,    // 指定文本标注所在的地理位置
				  offset   : new BMap.Size(30, -30)    //设置文本偏移量
				}
				var label = new BMap.Label(output, opts);  // 创建文本标注对象
					label.setStyle({
						 color : "black",
						 fontSize : "12px",
						 height : "20px",
						 lineHeight : "20px",
						 fontFamily:"微软雅黑"
					 });
				map.addOverlay(label); 
				
				var marker = new BMap.Marker(p2);  // 创建标注
				map.addOverlay(marker);               // 将标注添加到地图中
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				
				
			}else if(status == 4){
				
				var	output="派送中：";
				output +="\n"; 
				output +="您的货物正在派送中，请注意验收。"; 

				var opts = {
				  position : p2,    // 指定文本标注所在的地理位置
				  offset   : new BMap.Size(30, -30)    //设置文本偏移量
				}
				var label = new BMap.Label(output, opts);  // 创建文本标注对象
					label.setStyle({
						 color : "black",
						 fontSize : "12px",
						 height : "20px",
						 lineHeight : "20px",
						 fontFamily:"微软雅黑"
					 });
				map.addOverlay(label); 
				
				var marker = new BMap.Marker(p2);  // 创建标注
				map.addOverlay(marker);               // 将标注添加到地图中
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				
				
			}else if(status == 5){
				
				var	output="已送达：";
				output +="\n"; 
				output +="您的货物已经送达，谢谢您的惠顾。"; 

				var opts = {
				  position : p3,    // 指定文本标注所在的地理位置
				  offset   : new BMap.Size(30, -30)    //设置文本偏移量
				}
				var label = new BMap.Label(output, opts);  // 创建文本标注对象
					label.setStyle({
						 color : "black",
						 fontSize : "12px",
						 height : "20px",
						 lineHeight : "20px",
						 fontFamily:"微软雅黑"
					 });
				map.addOverlay(label); 
				
				  var marker = new BMap.Marker(p3);  // 创建标注
				  map.addOverlay(marker);               // 将标注添加到地图中
				  marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				
			     
			}
			
		}
	}, city); 
	
	
	
 	var navigationControl = new BMap.NavigationControl({
	    // 靠左上角位置
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    // LARGE类型
	    type: BMAP_NAVIGATION_CONTROL_LARGE,
	    // 启用显示定位
	    enableGeolocation: true
	  });
	  map.addControl(navigationControl);
	  // 添加定位控件
	  var geolocationControl = new BMap.GeolocationControl();
	  geolocationControl.addEventListener("locationSuccess", function(e){
	    // 定位成功事件
	    var address = '';
	    address += e.addressComponent.province;
	    address += e.addressComponent.city;
	    address += e.addressComponent.district;
	    address += e.addressComponent.street;
	    address += e.addressComponent.streetNumber;
	    alert("当前定位地址为：" + address);
	  });
	  geolocationControl.addEventListener("locationError",function(e){
	    // 定位失败事件
	    alert(e.message);
	  });
	  map.addControl(geolocationControl);
	 
	

</script>
