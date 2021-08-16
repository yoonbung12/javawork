$(document).ready(function(){
			${'#memberid'}.focusin(function(){
							${'#msg'}.addClass('display_none');
							${'#msg'}.removeClass('color_blue');
							${'#msg'}.removeClass('color_red');
						
							${this}.val('');
			});
			
			${'#memberid'}.focusout(function(){
						$.ajax({
										url : 'idCheck',
										type : 'post',
										data : {
													mid : $(this).val()
						
							},
							
							beforeSend : function(){
										$('#loadingimg').removeClass('display_none');
							},
							
							success : function(data){
											if(data == 'Y') {
															$('#msg').html('사용가능');
															$('#msg').addClass('color_blue');
															$('#msg').removeClass('display_none');
															
											} else {
															$('#msg').html('사용불가능');
															$('#msg').addClass('color_red');
															$('#msg').removeClass('display_none');
											}
							},
							
							error : function(request, status, error){
											alert('서버 통신에 문제가 발생했습니다. 다시 실행해주세요.');
											console.log(request);
											console.log(status);
											console.log(error);
							},
							
							complete : function(){
											$('#loadingimg').addClass('dispaly_none');
							}
																	
					});
			
			});
});