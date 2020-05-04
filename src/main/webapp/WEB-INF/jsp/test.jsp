<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="script.html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<title>批量地址</title>
	<style type="text/css">
		#l-map{height:300px;width:100%;}
		#r-result{width:100%; font-size:14px;line-height:20px;}
	</style>
	<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=m0mhkoxTVrCGRBNARuoN46q8jrXkE87b"></script>
</head>
<body>
	<div id="l-map"></div>
	<div id="r-result">
		<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="bdGEO()" >地址解析</a>
		<div id="result"></div>
	</div>
	<table>
	<tr>
					<td class="text-title">所在地市：</td>
					<td class="text-content">
						<input type="text" name="city" class="easyui-textbox theme-textbox-radius">
					</td>
				
					<td class="text-title">地址：</td>
					<td class="text-content">
						<input type="text" name="address" class="easyui-textbox theme-textbox-radius" >
					</td>
	</tr>
	</table>
</body>
</html>

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	var point = new BMap.Point(114.358832,34.813491);
	map.centerAndZoom(point,12);
	var myGeo = new BMap.Geocoder();

	
	function bdGEO(){
		var city = $('input[name="city"]').val();
		var add = $('input[name="address"]').val();
		alert(city);
		alert(add);
		var address = new BMap.Point(113.000000, 34.000000);
		var test = "adsad";
		myGeo.getPoint(add, function(point){
			if (point) {
				alert(test);
				map.centerAndZoom(point, 12);
				map.addOverlay(new BMap.Marker(point));
				alert(point.lng);
				alert(point.lat);
				//
				var address = new BMap.Point(point.lng, point.lat);
				
			}
		},city);

		
	}

</script>
