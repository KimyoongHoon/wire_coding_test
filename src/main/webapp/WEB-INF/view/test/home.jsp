<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.test.utils.Constants" %>
<head>
<style type="text/css">

body {

	margin-top : 10px;
	margin-left : 30px;
}

#title {
	font-size : 60px;
	font-weight: bold;
}
.submit-content {
	font-size : 25px;
	margin-top: 10px;
}
#submit {
	font-size : 25px;
	font-weight : bold;
	width : 200px;
	height: 50px;
}
#wrong {
	color: red;
	font-size : 15px;
}

</style>
</head>
<body>
<div>
	<div id="title">환율 계산</div>
</div>
<br>
<br>

<div>
	<div class="submit-content">송금 국가: 미국(USD)</div>
</div>
<div>
	<div class="submit-content">수취국가: 
		<select id="div_apv_sq" >
			<option value="USDKRW" >한국(KRW)</option>
			<option value="USDJPY" >일본(JPY)</option>
			<option value="USDPHP" >필리핀(PHP)</option>
		</select>
	</div>

</div>
<div>
	<div style="display : inline-block" class="submit-content">환율:  
	
		<div id="currency" style="display : inline-block"></div> 
	
	</div>
</div>
<div>
	<div class="submit-content">송금액:
		<input id="input-data" type="number"></input> USD
		<div id="wrong"></div> 
	</div>
</div>
<br>
<button id="submit">submit</button>
<br>
<br>
<div id="result" class="submit-content"></div>


</body>
<script src="/js/jquery-2.2.4.js"></script>
<script type="text/javascript">
$(function() {

	//환율 정보 오브젝트
	var currenObj = null;

	//송금액 체크 여부
	var pass = false;

	//송금액
	var inputData = null;

	//환율
	var currenty = null;

	//단위
	var unit = 'KRW';

	//송금액 최소 단위
	const min = 1;

	//송금액 최대 단위
	const max = 10000;
	
	init();

	//환율정보를 가져옴.
	function init(){

		 $.ajax({
			type: "get"
			,url: "/test/getCurrency"
			,dataType: 'json'
			,success: function(res){					

				if(res.result == "<%=Constants.RESULT.SUCCESS%>"){
					currenObj = JSON.parse(res.msg);

					//처음 한국으로 시작
					currenty = currenObj['USDKRW'];
					unit = 'USDKRW'.replace('USD','');
					
					$("#currency").html(numberWithCommas(currenty) + ' ' + unit +'/USD');

					//셀렉트 이벤트 추가
					$("#div_apv_sq").change(function(){
						currenty = currenObj[$(this).val()];

						unit = $(this).val().replace('USD','');
						
						$("#currency").html(numberWithCommas(currenty) + ' ' + unit +'/USD');


						//송금액이 입력되었을때 수취국가를 바껏을때 수취금액 자동변경.
						if(pass){
							dataSetting();
						}
						
					});
					
				}else{
					alert("API 조회에 실패하셨습니다.");
				}															
			}				
		}); 
	}

	//input 이벤트
	$("#input-data").on("change keyup paste" , function(){

		inputData = $(this).val();

		if( inputData < min || inputData > max){

			$("#wrong").html( min+'부터 '+ max +'까지 값을 입력해주세요.');
			pass = false;
			
		} else {
			pass = true;
			$("#wrong").html('');
		}
		
	});

 	//submit 버튼 이벤트
	$("#submit").on('click' ,function(){

		dataSetting();
	});


 	//데이터 계산
	function dataSetting(){

		if(!pass){
			alert('송금액이 ' +min+'~' + max +' 값이 아닙니다.');
			return;
		}

		var result = numberWithCommas(currenty * inputData); 
		$("#result").html('수취금액은 ' + result + ' ' +unit +' 입니다.');

	}
	
	//소수점 둘째까지 버리고 3자리수마다 ,를 찍음.
	//참고 : https://lehero.tistory.com/263
	function numberWithCommas(n) {
		n =  Math.floor(n * 100) / 100;
	    var parts=n.toString().split(".");
	    return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
	}


});
</script>
