var SITE_SHOP_DETAIL = function(){
	var CONST = {
		WIDTH_MOBILE: 768,
		TAB_TYPE: {
			DETAIL: 'detail',
			QNA: 'qna',
			REVIEW: 'review',
			RETURN: 'return'
		}
	};

	var $body;
	var $selected_options;
	var $prod_detail;
	var $prod_detail_content_mobile;
	var $prod_detail_content_pc;
	var $prod_detail_content_tab_mobile;
	var $prod_detail_wish_count;
	var $prod_detail_wish_count_mobile;
	var $prod_detail_return;
	var $first_photo_review_wrap;
	var $prod_image_list;
	var $prod_image_list_rolling;
	var $review_image_list;
	var $review_image_list_rolling;
	var $review_summary_wrap;
	var $review_summary_wrap_mobile;
	var $prod_goods_form;
	var $add_cart_alarm;
	var $deliv_visit_wrap;
	var $options;
	var $prod_deliv_setting;
	var selected_options = []; /** [ {options:[{option_code:, value_code:, value_name:}], price:, count:, require:} ] */
	var selected_require_options = []; /** [ { value_type:SELECT(선택형)/INPUT(입력형), option_code:, value_code:, value_name:  } ] */
	var require_option_count = 0;
	var require_input_option_count = 0;
	var current_prod_idx = 0;
	var prod_stock_use = false;
	var prod_stock = 0;
	var prod_stock_unlimit = false;
	var prod_price = 0;
	var order_count = 0;
	/** 구매할 수량 (옵션이 없는 경우에만)(*/
	var isComplete = false;
	var current_content_tab = '';
	/** detail,review,qna*/
	var isUseNpMobile = false;
	/** 모바일 네이버페이 사용 유무*/
	var $deliv_type = '';
	var $deliv_pay_type = '';
	/** 배송 국가 (옵션 select 커스텀 변경) */
	var $deliv_country;

	/* 구매 수량 체크를 위한 변수 */
	var max_prod_quantity = 0;
	var max_member_quantity = 0;
	var optional_limit = 0;
	var optional_limit_type = 'relative';

	var total_price_localize_text = '';		// 총금액 다국어 코드

	var add_order_progress_check = false;

	var use_lazy_load = true; //레이지 로드 사용여부

	var pc_tab_type = 'Y'; // pc 버전 탭 타입

	var is_digital_prod = false;

	// 상품 상세페이지 내 기획전 위젯의 cart 버튼을 클릭하여 모달을 띄우는 동작에서
	// 모달이 닫힐 시 설정 값의 초기화를 위해 변수를 설정
	var prod_idx_org = 0;
	var prod_price_org = 0;
	var require_option_count_org = 0;
	var use_lazy_load_org = true;
	var pc_tab_type_org = 'Y';
	var is_site_page_org = true;
	var is_digital_prod_org = false;

	var is_view_price = true;
	var paging_type = 'st00';
	var paging_default_type = 'st00';
	var paging_active_type = 'st00';
	var cm_data = {};
	var section_code;

	var prod_edit_time = '';
	var prod_deliv_hash = '';
	var shop_view_style = '';

	var handle_loadDelivSetting = 0;
	var prod_option_touching = false;

	var first_tab = '';
	var use_tab_list = [];

	var default_options = {
		prod_idx: 0,
		prod_price: 0,
		require_option_count: 0,
		require_input_option_count: 0,
		shop_use_full_load: false,
		shop_pc_tab_type_one_page: false,
		is_site_page: false,
		is_disital: false,
		is_prod_detail_page: false,
		is_price_view_permission: false,
		cm_style: '{}',
		section_code: '',
		exist_color_option: false,
		is_exist_color_option_images: false,
		first_tab: '',
	};
	var options = {};

	var is_init_detail = false;
	var last_refund_data;

	/*
	option 샘플
	initDetail({
		prod_idx: ,
		prod_price: ,
		require_option_count: ,
		shop_use_full_load: ,
		shop_pc_tab_type_one_page: ,
		is_site_page: ,
		is_disital: ,
		is_prod_detail_page: ,
		is_price_view_permission: ,
		cm_style: ,
		section_code:
		exist_color_option:
		is_empty_color_option_images:
	});
	*/
	var initDetail = function(_option){
		options = $.extend({}, default_options, _option);

		var prodIdx = options.prod_idx;
		var price = options.prod_price;
		var requireOptionCnt = options.require_option_count;
		var requireInputOptionCnt = options.require_input_option_count;
		var _use_lazy_load = options.shop_use_full_load;
		var tab_type = options.shop_pc_tab_type_one_page;
		var is_site_page = options.is_site_page;
		var is_digital = options.is_disital;
		var is_prod_detail_page = options.is_prod_detail_page;
		var view_price = options.is_price_view_permission;
		var _cm_data = options.cm_style;
		section_code = options.section_code;
		prod_edit_time = options.prod_edit_time;
		prod_deliv_hash = options.prod_deliv_hash;
		shop_view_style = options.shop_view_style;
		first_tab = options.first_tab;
		use_tab_list = options.use_tab_list;

		$body = $('body');

		if( is_prod_detail_page ) {
			var $target_modal = $('.modal_prod_detail_from_shopping_list');
			$prod_image_list = $target_modal.find('#prod_image_list');
			$prod_image_list_rolling = $prod_image_list.find('div.owl-carousel');
			$prod_goods_form = $target_modal.find('#prod_goods_form');
			$selected_options = $target_modal.find('#prod_selected_options');
			$options = $target_modal.find('#prod_options');
			$prod_deliv_setting = $target_modal.find('#prod_deliv_setting');
			$prod_detail = $target_modal.find('#prod_detail');
			$prod_detail_content_pc = $prod_detail.find('._prod_detail_detail_lazy_load');
			$prod_detail_content_mobile = $target_modal.find('#prod_detail_content_mobile');
			$prod_detail_content_tab_mobile = $target_modal.find('#prod_detail_content_tab_mobile');
			$prod_detail_wish_count = $target_modal.find('#prod_detail_wish_count');
			$prod_detail_wish_count_mobile = $target_modal.find('#prod_detail_wish_count_mobile');
			$add_cart_alarm = $target_modal.find('#shop_detail_add_cart_alarm');
			$deliv_visit_wrap = $target_modal.find('#deliv_visit_wrap');
		} else {
			$prod_image_list = $('#prod_image_list');
			$prod_image_list_rolling = $('#prod_image_list').find('div.owl-carousel');
			$prod_goods_form = $('#prod_goods_form');
			$selected_options = $('#prod_selected_options');
			$options = $('#prod_options');
			$prod_deliv_setting = $('#prod_deliv_setting');
			$prod_detail = $('#prod_detail');
			$prod_detail_content_pc = $prod_detail.find('._prod_detail_detail_lazy_load');
			$prod_detail_content_mobile = $('#prod_detail_content_mobile');
			$prod_detail_content_tab_mobile = $('#prod_detail_content_tab_mobile');
			$prod_detail_wish_count = $('#prod_detail_wish_count');
			$prod_detail_wish_count_mobile = $('#prod_detail_wish_count_mobile');
			$add_cart_alarm = $('#shop_detail_add_cart_alarm');
			$deliv_visit_wrap = $('#deliv_visit_wrap');
		}

		$prod_detail_return = $("#prod_detail_return_body");

		$first_photo_review_wrap = $prod_detail.find('._first_photo_review_wrap');
		$review_summary_wrap = $prod_detail.find('._review_summary_wrap');
		$review_summary_wrap_mobile = $prod_detail.find('._review_summary_wrap_mobile');

		is_view_price = view_price;
		pc_tab_type = tab_type;
		current_prod_idx = prodIdx;
		prod_price = price;
		require_option_count = parseInt(requireOptionCnt);
		require_input_option_count = parseInt(requireInputOptionCnt);
		selected_options = [];
		selected_require_options = [];
		isComplete = true;
		//isUseNpMobile = use_np_mobile;
		use_lazy_load = _use_lazy_load;
		cm_data = JSON.parse(_cm_data);

		if(!is_prod_detail_page){
			prod_idx_org = current_prod_idx;
			prod_price_org = prod_price;
			require_option_count_org = require_option_count;
			use_lazy_load_org = use_lazy_load;
			pc_tab_type_org = pc_tab_type;
			is_site_page_org = is_site_page;
			is_digital_prod_org = is_digital;
		}

		var window_width = $(window).width();
		var is_mobile_width = ( window_width < CONST.WIDTH_MOBILE );

		/* 만약 PC버전이면서, 하나의 페이지에 모든 탭 열람일 경우 상품 상세설명과 반품/교환 HTML 세팅 */
		var is_shop_pc_tab_type_one_page = ( options.shop_pc_tab_type_one_page == 'Y' );
		if ( !is_mobile_width && is_shop_pc_tab_type_one_page ) {
			var pc_detail_html = IMWEB_TEMPLATE.loadSimple("prodDetailPC");
			$prod_detail_content_pc.html(pc_detail_html);
		}

		runLazyload();
		if( is_digital == true ) is_digital_prod = true;

		var hash_temp = location.hash.split('!/');
		switch( hash_temp[0] ) {
			case '#prod_detail_detail':
				if(is_mobile_width || !is_shop_pc_tab_type_one_page) SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.DETAIL);
				break;
			case '#review_detail':
				if(is_mobile_width || !is_shop_pc_tab_type_one_page) SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.REVIEW);
				if( hash_temp[1] ) {
					SITE_SHOP_DETAIL.viewReviewDetail(hash_temp[1], 1, 'N', 'Y');
				}
				break;
			case '#qna_detail':
				if(is_mobile_width || !is_shop_pc_tab_type_one_page) SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.QNA);
				if( hash_temp[1] ) {
					SITE_SHOP_DETAIL.viewQnaDetail(hash_temp[1], 1, 'Y');
				}
				break;
			case '#return_detail':
				if(is_mobile_width || !is_shop_pc_tab_type_one_page) SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.RETURN);
				break;
			default:
				var target_tab;
				switch ( options.first_tab ) {
					case 'prod_review':
						target_tab = CONST.TAB_TYPE.REVIEW;
						break;
					case 'prod_qna':
						target_tab = CONST.TAB_TYPE.QNA;
						break;
					case 'prod_return':
						target_tab = CONST.TAB_TYPE.RETURN;
						break;
					case 'prod_detail':
					default:
						target_tab = CONST.TAB_TYPE.DETAIL;
				}

				SITE_SHOP_DETAIL.changeContentPCTab(target_tab);
				if( is_site_page ) SITE_SHOP_DETAIL.changeContentTab(target_tab);
				break;
		}

		if(cm_data.shop_view_buy_item_tooltip === 'Y'){
			$('.btn_tooltip[data-toggle="tooltip"]').tooltip({
				delay : {show : 500, hide : 1000000}
			});

			var $tooltip = $('.btn_tooltip[data-toggle="tooltip"]');
			if($tooltip.length > 0){
				var tooltip_y = $tooltip[0].getBoundingClientRect().y;
				if(tooltip_y >= 0 && tooltip_y < $(window).height()){
					// 툴팁이 화면 내에 보이지 않을 때 show하면 위치가 어긋나게 됨
					setTimeout(function(){
						$tooltip.tooltip('show');
					}, 100);
				}

				$(window).scroll(function(){
					if($tooltip.next('.tooltip:visible').length === 0){
						var tooltip_y = $tooltip[0].getBoundingClientRect().y;
						if(tooltip_y > 0 && tooltip_y < ($(window).height() - $tooltip.height())){
							$tooltip.tooltip('show');
						}
					}
				});
			}
		}

		setTimesale();
		if(!is_prod_detail_page){
			setImgZoom(30, 800, section_code);

			if($first_photo_review_wrap.length > 0){
				getFirstPhotoReview(1);
			}
		}

		is_init_detail = true;
	};

	var initProdStock = function(stock_use, stock, stock_un_limit) {
		prod_stock_use = stock_use;
		prod_stock = stock;
		prod_stock_unlimit = stock_un_limit;
	};

	var runLazyload = function(){
		if(!use_lazy_load)
			return false;


		$prod_detail_content_pc.find('img').lazyload({		/* 상품 상세페이지 lazy load 적용, 기본 /img/no-image.png 는 한번만 불러옴 */
			placeholder : NO_IMAGE_URL,
			threshold : 100,
			effect : "fadeIn",
			load : function(){
				$(this).removeAttr('height');
			}
		});
	};

	var setImgZoom = function(margin, max_width, section_code){
		if($('.shop_view .xzoom').length > 0){
			var shop_view_xzoom = $('.shop_view .xzoom, .shop_view .xzoom-gallery').xzoom({Xoffset: margin, openOnSmall: false, defaultScale: 0, scroll: false, custom: true, hover: true});
			if(typeof shop_view_xzoom != 'undefined'){
				// 연관상품 장바구니 확인 후 닫을 시 재실행되면서 오류나는 문제 방지
				shop_view_xzoom.eventmove = function(element){
					var $xzoom_preview = $('.shop_view .xzoom-preview');
					$xzoom_preview.addClass(section_code);
					var $xzoom_preview_img = $xzoom_preview.find('img');
					var $xzoom = $('.shop_view .xzoom');
					var xzoom_width = $(window).width() - ($xzoom.offset().left + $xzoom.outerWidth() + margin * 2);
					if(xzoom_width > max_width){
						xzoom_width = max_width;
					}
					var xzoom_height = xzoom_width;
					var xzoom_preview_img_width = $xzoom_preview_img.outerWidth();
					var xzoom_preview_img_height = $xzoom_preview_img.outerHeight();
					if(xzoom_preview_img_width < xzoom_width || xzoom_preview_img_height < xzoom_height){
						shop_view_xzoom.closezoom();
					}else{
						$xzoom_preview.outerWidth(xzoom_width);
						$xzoom_preview.outerHeight(xzoom_height);
						element.bind('mousemove', shop_view_xzoom.movezoom);
					}
					$xzoom_preview.mouseover(shop_view_xzoom.closezoom);
				};
			}
		}
	};

	var setTimesale = function(){
		var $doz_timesale_wrap = $('#prod_goods_form ._doz_timesale_wrap');
		if($doz_timesale_wrap.length > 0){
			$doz_timesale_wrap.each(function(){
				var $that = $(this);
				var $doz_timesale = $that.find('._doz_timesale');
				var start_time = ($that.find('._doz_timesale').attr('data-start-time') * 1000);
				var timesale_interval = setInterval(function(){
					var remain_ms = ($doz_timesale.attr('data-end-time') * 1000) - start_time;
					if(remain_ms > 0){
						var remain_d = Math.floor(remain_ms / 86400000);
						var remain_h = Math.floor((remain_ms % 86400000) / 3600000);
						var remain_m = Math.floor((remain_ms % 3600000) / 60000);
						var remain_s = Math.floor((remain_ms % 60000) / 1000);

						var remain_hh = remain_h < 10 ? '0' + remain_h : '' + remain_h;
						var remain_mm = remain_m < 10 ? '0' + remain_m : '' + remain_m;
						var remain_ss = remain_s < 10 ? '0' + remain_s : '' + remain_s;

						if(remain_d >= 1){
							$doz_timesale.html(getLocalizeString('설명_상세페이지타임세일종료까지n일', [remain_d], "<label class='text-bold text-brand'>타임세일</label> 종료까지 %1일"));
						}else{
							$doz_timesale.html(getLocalizeString('설명_상세페이지타임세일종료까지n1시n2분n3초남음', [remain_hh, remain_mm,remain_ss], "<label class='text-bold text-brand'>타임세일</span> 종료까지 <strong>%1:%2:%3</strong> 남음"));
						}
						start_time = start_time + 1000;
					}else{
						/* 타임세일 종료 */
						clearInterval(timesale_interval);
						$that.remove();
					}
				}, 1000);
			});
		}
	}

	/**
	 * 다국어코드를 백엔드에서 호출하여 자바스크립트에 저장하는 함수
	 * initDetail 피라미터 개수가 다른 히스토리를 모르겠어서 일단 따로 만들었음
	 * @param code
	 */
	var initLocalize = function(code){
		total_price_localize_text = code;
	};

	/**
	 * 특정상품 위시리스트 추가 처리
	 * @param prod_code
	 */
	var addProdWish = function(prod_code){
		$.ajax({
			type : 'POST',
			data : {'prod_code' : prod_code},
			url : ('/shop/add_prod_wish.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					if(res.res == 'add'){
						if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.AddToWishlist();
						if ( typeof CHANNEL_PLUGIN != "undefined" ) CHANNEL_PLUGIN.AddToWishlist();
						$prod_detail_wish_count.parent().addClass('active');
						$prod_detail_wish_count_mobile.parent().addClass('active');
					}else if(res.res == 'delete'){
						$prod_detail_wish_count.parent().removeClass('active');
						$prod_detail_wish_count_mobile.parent().removeClass('active');
						if ( typeof CHANNEL_PLUGIN != "undefined" ) CHANNEL_PLUGIN.addCountUserProfileAttr('wishCount', -1);
					}
					$prod_detail_wish_count.text(res.wish_cnt);
					$prod_detail_wish_count_mobile.text(res.wish_cnt);
				}else
					alert(res.msg);
			}
		});
	};
	/**
	 * 특정상품 위시정보 가져오기 (내 위시여부,위시갯수)
	 * @param prod_code
	 */
	var getProdWish = function(prod_code){
		$.ajax({
			type : 'POST',
			data : {'prod_code' : prod_code},
			url : ('/shop/get_prod_wish.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
				}else
					alert(res.msg);
			}
		});
	};
	/**
	 * 두개의 옵션 데이터가 같은지 확인 (순서 무관)
	 * @param options1 [ {option_code: option_value} ]
	 * @param options2 [ {option_code: option_value} ]
	 */
	var isSameOptionList = function(options1, options2){
		if(options1.length == options2.length){
			var isSame = true;
			var exist = false;
			$.each(options1, function(no, data){
				exist = false;
				$.each(options2, function(no2, data2){
					if(data.option_code == data2.option_code){
						if (data.value_code!=''){
							if(data.value_code == data2.value_code){
								exist = true;
								return false;
							}
						}else{
							if(data.value_name== data2.value_name){
								exist = true;
								return false;
							}
						}
					}
				});
				if(!exist){
					isSame = false;
					return false;
				}
			});
			return isSame;
		}else{
			return false;
		}
	};

	/**
	 * 상품 이미지 롤링 시작
	 */
	var startProdImageRolling = function(autoWidth){

		var items = $prod_image_list.find('._item');
		var is_dots = false;
		is_dots = items.length > 1 ? true : false;

		if(is_dots){
			switch(cm_data.paging_style_type){
				case 'st00':
					switch(cm_data.paging_default_style_type){
						case 'st00':
							$prod_image_list_rolling.toggleClass('paging_type_dot paging_type_dot01', true);
							break;
						case 'st01':
							$prod_image_list_rolling.toggleClass('paging_type_dot paging_type_dot02', true);
							break;
					}
					break;
				case 'st01':
					$prod_image_list_rolling.toggleClass('paging_type_big_dot', true);
					break;
				case 'st02':
					$prod_image_list_rolling.toggleClass('paging_type_line', true);
					break;
				case 'st03':
					switch(cm_data.paging_active_style_type){
						case 'st00':
							$prod_image_list_rolling.toggleClass('paging_type_count paging_type_count01', true);
							break;
						case 'st01':
							$prod_image_list_rolling.toggleClass('paging_type_count paging_type_count02', true);
							break;
						case 'st02':
							$prod_image_list_rolling.toggleClass('paging_type_count paging_type_count03', true);
							break;
					}
					break;
			}
		}

		if(autoWidth){
			$prod_image_list_rolling.owlCarousel({
				dots : is_dots,
				navigation : true,
				slideSpeed : 300,
				paginationSpeed : 400,
				singleItem : true,
				animateOut : 'fadeOut',
				items : 1,
				autoHeight: true,
				autoWidth: true,
				onInitialized: function(){
					var owl_item_list = $prod_image_list.find('.owl-item');
					var first_url = $(items[0]).find('img').attr('src');
					var max_width = 750;
					// TODO is_prod_detail_page가 명확하지 않아 장바구니 임시조치
					// shopping_list에서 들어오면 400으로 잡아야 함
					if($('.modal_prod_detail_from_shopping_list').length > 0){
						var max_width = 400;
					}else if($prod_detail.width() < max_width){
						max_width = $prod_detail.width();
					}
					$.each(owl_item_list, function(key, value){
						var $value = $(value);
						var image_url = $value.find('img').attr('src');
						var image = new Image();
						image.onload = function(){
							var that = this;
							// owl initialized 이후 내부적으로 resized를 하면서 크기가 어긋나는 경우가 있어 timeout을 둠
							setTimeout(function(){
								var org_width = that.width;
								var org_height = that.height;
								var width = org_width > max_width ? max_width : org_width;
								var height = width * (org_height / org_width);
								$value.css({'width': width + 'px', 'height': height + 'px'});
								if(that.src === first_url){
									// 첫 이미지 크기 세팅
									$prod_image_list.find('.owl-carousel').css({'width': width, 'height' : height});
									$prod_image_list.find('.owl-height').css({'height' : height});
								}
							}, 5);
						};
						image.src = image_url;
					});
				},
				onChanged: function(){
					var owl = $prod_image_list_rolling.data('owlCarousel');
					var current = 0;
					if(typeof owl !== "undefined") current = owl._current;
					var li_list = $prod_image_list.find('li.owl-dot');
					li_list.find('a').removeClass('active');
					li_list.eq(current).find('a').addClass('active');
					if(items.length > 1){
						$prod_goods_form.find('header').css('margin-top', 0)
					}
					// 현재 이미지 너비로 슬라이드 너비 수정
					var current_width = $(items[current]).find('img').css('width');
					$prod_image_list.find('.owl-carousel').css({'width': current_width, 'height': ''});
				}
			});
		}else{
			$prod_image_list_rolling.owlCarousel({
				dots : is_dots,
				navigation : true,
				slideSpeed : 300,
				paginationSpeed : 400,
				singleItem : true,
				animateOut : 'fadeOut',
				items : 1,
				autoHeight: false,
				onChanged : function(){
					var owl = $prod_image_list_rolling.data('owlCarousel');
					var current = 0;
					if(typeof owl !== "undefined") current = owl._current;
					var li_list = $prod_image_list.find('li.owl-dot');
					li_list.find('a').removeClass('active');
					li_list.eq(current).find('a').addClass('active');
					if(items.length > 1){
						$prod_goods_form.find('header').css('margin-top', 0)
					}
				}
			});
		}
	};

	/**
	 * 상품 이미지 롤링 특정 위치 이동
	 * @param no
	 */
	function changeProdImageRolling(no){
		var owl = $prod_image_list_rolling.data('owlCarousel');
		if(typeof owl !== "undefined"){
			owl.to(no);

			var option_img_list = $prod_image_list.find('._color_option_img');
			if ( option_img_list.length ) {
				$prod_image_list.attr('data-preview-type', 'prod');
				option_img_list.attr('data-visible', 'false');
			}
		}
	}

	var loadOption = function(type, prod_idx){
		$.ajax({
			type : 'POST',
			data : {'type' : type, 'prod_idx' : prod_idx, 'selected_require_options' : selected_require_options, '__': prod_edit_time},
			url : ('/shop/load_option.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					max_prod_quantity = result.max_prod_quantity;
					max_member_quantity = result.max_member_quantity;
					optional_limit = result.optional_limit;
					optional_limit_type = result.optional_limit_type;
					$options.html(result.option_html);

					// 색상 옵션이 있고 + 색상 옵션의 이미지까지 있다면
					if ( options.exist_color_option && options.is_exist_color_option_images ) {
						if ( $prod_image_list.find('.xzoom').length ) {
							// xzoom 사용
							$options.find('label[data-opttype]').hover(function() {
								var $_that = $(this);
								var $_target_wrap = $("#btn_owl_" + $_that.attr('data-optcode'));
								if ( ! $_target_wrap.length ) {
									$_target_wrap = $("#btn_owl_0");
								}
								$_target_wrap.find('a').trigger('click');
							});
						} else {
							// xzoom 미사용2
							var $_color_option_img = $prod_image_list.find('._color_option_img');
							if ( $_color_option_img.length ) {
								(function() {
									var hover_evnets = ( IS_MOBILE ? 'touchstart touchend' : 'mouseover');
									$options.find('label[data-opttype] ._bg').off(hover_evnets).on(hover_evnets, function(e) {
										// mouse over
										var $_that = $(this);
										var opt_code = $_that.attr('data-optcode');
										var $_target = $_color_option_img.filter('[data-optcode="' + opt_code + '"]');

										$_color_option_img.attr('data-visible', 'false');

										if ( $_target.length ) {
											if ( $_target.attr('src').length == 0 ) {
												$_target.attr('src', $_target.attr('data-src'));
												// 이미지 load 이벤트
												$_target.on('load', function() {
													var _width = this.naturalWidth;
													var _height = this.naturalHeight;

													if ( _width == _height ) {
														var _wrap_width = $prod_image_list.width();
														var _wrap_height = $prod_image_list.height();

														if ( _wrap_width == _wrap_height ) {
															$(this).attr('data-size-type', 'A');
														} else if ( _wrap_width > _wrap_height) {
															$(this).attr('data-size-type', 'B');
														} else {
															$(this).attr('data-size-type', 'C');
														}
													} else if ( _width > _height ) {
														$(this).attr('data-size-type', 'B');
													} else {
														$(this).attr('data-size-type', 'C');
													}
												})
											}
											$prod_image_list.attr('data-preview-type', 'option');
											$_target.attr('data-visible', 'true');
										}

										switch( e.type ) {
											case 'touchstart':
												prod_option_touching = true;
												break;
											case 'touchend':
												prod_option_touching = false;
												break;
										}
									});

									$options.find('label[data-opttype] ._bg').off('mouseout').on('mouseout', function(e) {
										// mouse out
										var $_that = $(this);
										var opt_code = $_that.attr('data-optcode');
										var $_target = $_color_option_img.filter('[data-optcode="' + opt_code + '"]');

										$prod_image_list.attr('data-preview-type', 'prod');

										if($_target.length){
											$_target.attr('data-visible', 'false');
										}
									});

									if ( IS_MOBILE ) {
										// 모바일일 경우 터치이벤트 추가
										var $_body = $("#doz_body");
										if ( !$_body.hasClass('_bind_touchstart_evt') ) {
											$_body.on('touchstart', function(e) {
												if ( !prod_option_touching ){
													$prod_image_list.attr('data-preview-type', 'prod');
													if ( $_color_option_img.length ) {
														$_color_option_img.attr('data-visible', 'false');
													}
												}
											}).addClass('_bind_touchstart_evt');
										}
									}
								})();
							}
						}
					}

					if(IS_MOBILE){
						addEventMobileOptionInput();
					}
				}else{
					alert(result.msg);
				}
			}
		});
	};

	var loadDelivSetting = function(prod_idx,change_country,deliv_type,deliv_pay_type){
		$deliv_country = change_country;
		$deliv_pay_type = deliv_pay_type;
		JS_DELAY('loadDelivSetting', function() {
			var window_width = $(window).width();
			var is_mobile_width = ( window_width < CONST.WIDTH_MOBILE );
			var is_design_mode = ( location.pathname.indexOf('/admin/design/') != -1 );
			$.ajax({
				type : 'GET',
				data : {'prod_idx' : prod_idx, 'change_country' : change_country, 'deliv_type' : deliv_type, 'deliv_pay_type' : deliv_pay_type, '__': MEMBER_HASH},
				url : ( is_mobile_width ? '/shop/load_deliv_setting.cm' : '/shop/load_deliv_setting_pc.cm' ),
				dataType : 'json',
				cache : !is_design_mode,
				async: false,
				success : function(result){
					if( result.msg == 'SUCCESS' ) {
						$prod_deliv_setting.html(result.deliv_html);
						if( is_mobile_width ) {
							$prod_detail.find('._today_arrival_wrap').html(result.today_arrival_html);
						}

						/* 환불 정보 입력 */
						if ( result.refund ) {
							last_refund_data = result.refund;

							if ( is_mobile_width ) {
								if ( current_content_tab == CONST.TAB_TYPE.RETURN ) {
									/* 현재 탭이 반품/교환 일 때만 갱신 */
									if ( is_mobile_width ) {
										SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.RETURN);
									} else {
										var prod_detail_return_html = IMWEB_TEMPLATE.loadSimple("prodReturnPc", last_refund_data);
										$prod_detail_return.html(prod_detail_return_html);
									}
								}
							} else {
								if ( options.shop_pc_tab_type_one_page == 'Y' ) {
									var prod_detail_return_html = IMWEB_TEMPLATE.loadSimple("prodReturnPc", last_refund_data);
									$prod_detail_return.html(prod_detail_return_html);
								} else {
									if ( current_content_tab == CONST.TAB_TYPE.RETURN ){
										SITE_SHOP_DETAIL.changeContentPCTab(CONST.TAB_TYPE.RETURN);
									}
								}
							}
						}

						$prod_detail.find('._item_detail_wrap').html(result.item_detail_html);

						if( IS_MOBILE ) {
							addEventMobileOptionInput();
						}

						if ( typeof naver != typeof void 0 && !! naver.NaverPayButton ) {
							makeNaverPayBtn('naverPayWrap', ( change_country == 'KR' || change_country == 'none' || change_country == ''));
						}

						if ( typeof kakaoCheckout != 'undefined' && typeof makeTalkPayButton != 'undefined') {
							var _target_container_id = ( is_mobile_width ? 'create-kakao-checkout-button-mobile' : 'create-kakao-checkout-button' );
							makeTalkPayButton(_target_container_id, ( change_country == 'KR' || change_country == 'none' || change_country == '' ));
						}

					}else{
						alert(result.msg);
					}
				}
			});
		}).debouncing(100);
	};

	/**
	 * 선택형 필수 옵션 선택
	 * @param type cart (장바구니변경), prod(상품상세)
	 * @param option_code
	 * @param value_code
	 */
	var selectRequireOption = function(type, prod_idx, option_code, value_code, value_name, success){
		var data = {'value_type':'SELECT', 'option_code' : option_code, 'value_code' : value_code, 'value_name' : value_name};
		var no = findSelectedRequireOption(option_code);
		if(no == -1){
			if(value_code == '') return;
			selected_require_options.push(data);
			/** 처음 선택된 옵션인 경우 새로 추가*/
		}else{
			if(value_code == ''){
				/** 옵션 삭제 */
				selected_require_options.splice(no, (selected_require_options.length - no));
			}else{
				selected_require_options[no] = data;
				/** 이미 선택된 옵션인 경우 기존 값 교체 */
				if(no < selected_require_options.length - 1){
					selected_require_options.splice(no + 1, (selected_require_options.length - (no + 1)));
				}
			}
		}
		if( selected_require_options.length == require_option_count){
			/** 필수 옵션이 모두 선택되었을 경우*/
			selectOption(prod_idx, selected_require_options, true, 1, function(){
				selected_require_options = [];
				loadOption(type, prod_idx);
				success();
			}, function(msg){
				alert(msg);
			});
		}else{
			/** 필수옵션 선택이 아직 끝나지 않았을 경우 옵션 재로드 */
			loadOption(type, prod_idx);
		}
	};

	/**
	 * 입력형 필수 옵션 변경시 처리
	 * @param type cart (장바구니변경), prod(상품상세)
	 */
	var changeRequireInputOption = function(type, prod_idx, option_code, msg, success){
		var data = {'value_type':'INPUT', 'option_code' : option_code, 'value_code':'', 'value_name' : msg};
		var no = findSelectedRequireOption(option_code);
		if(no == -1){
			if(msg == '') return;
			selected_require_options.push(data);
			/** 처음 입력한 옵션인 경우 새로 추가*/
		}else{
			if(msg == ''){
				/** 입력형 옵션 삭제 */
				selected_require_options.splice(no, (selected_require_options.length - no));
			}else{
				/** 기존값 교체 */
				selected_require_options[no] = data;
			}
		}
		if( selected_require_options.length == require_option_count){
			/** 필수 옵션이 모두 선택되었을 경우*/
			selectOption(prod_idx, selected_require_options, true, 1, function(){
				selected_require_options = [];
				loadOption(type, prod_idx);
				success();
			}, function(msg){
				alert(msg);
			});
		}else{
			if(IS_MOBILE && selected_require_options.length == require_input_option_count)  loadOption(type, prod_idx);
		}
	};

	/**
	 * 옵션 선택
	 * @param options [ {value_type: SELECT/INPUT, option_code:, value_code:, value_name:} ]
	 */
	var selectOption = function(prod_idx, options, require, count, success, failed){
		//$('.npay_btn_pay').attr('onclick', 'event.cancelBubble=true');
		var btn_buy_onclick_temp = $('._btn_buy').attr('onclick');
		var btn_cart_onclick_temp = $('._btn_cart').attr('onclick');
		$('._btn_buy').attr('onclick', 'event.cancelBubble=true');
		$('._btn_cart').attr('onclick', 'event.cancelBubble=true');
		$.ajax({
			type : 'POST',
			data : {'prod_idx' : prod_idx, 'options':options, 'require':(require?'Y':'N'), 'count':count},
			url : ('/shop/select_option.cm'),
			dataType : 'json',
			cache : false,
			async: false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					prod_price = result.prod_price;
					var no = findSelectedOption(options);
					if(no == -1){
						/* 상품 구매수량 체크 */
						var total_selected_count = getProdTotalQuantity();

						if ( max_prod_quantity == 0 || total_selected_count < max_prod_quantity ) {
							selected_options.push(result.selected_option);
							/** 처음 선택된 옵션인 경우 새로 추가*/
							success();
						} else {
							failed(LOCALIZE.설명_최대N개만구매가능한상품입니다(max_prod_quantity));
						}
					}else{
						failed(LOCALIZE.설명_이미선택된옵션입니다());
					}
				}else{
					failed(result.msg);
				}
				$('._btn_buy').attr('onclick', btn_buy_onclick_temp);
				$('._btn_cart').attr('onclick', btn_cart_onclick_temp);
			}
		});
	};

	var getProdTotalQuantity = function() {
		/* 상품 구매수량 체크 */
		var total_selected_count = 0;
		$.each(selected_options, function(idx, obj) {
			total_selected_count += obj.count; });
		return total_selected_count;
	};

	var getProdRequireTotalQuantity = function(){
		/* 필수 옵션 구매수량 체크 */
		var total_selected_count = 0;
		$.each(selected_options, function(idx, obj) {
			if(obj.require) total_selected_count += obj.count;
		});
		return total_selected_count;
	};

	/**
	 * 선택된 옵션 삭제
	 * @param optName
	 * @param success
	 */
	var removeSelectedOption = function(optNo, success){
		if(optNo <= (selected_options.length - 1)){
			selected_options.splice(optNo, 1);
			success();
		}
	};

	/**
	 * 해당 옵션이 현재 선택되어있는지 확인
	 * @param options [{option_code:, value_code:, value_name:}]
	 * */
	var findSelectedOption = function(options){
		var found_no = -1;
		$.each(selected_options, function(no, data){
			if(isSameOptionList(data.options, options)){
				found_no = no;
				return false;
			}
		});
		return found_no;
	};

	/**
	 * 해당 필수 옵션이 현재 선택되어있는지 확인
	 * @param optName
	 */
	var findSelectedRequireOption = function(option_code){
		var foundNo = -1;
		$.each(selected_require_options, function(no, data){
			if(data.option_code == option_code){
				foundNo = no;
				return false;
			}
		});
		return foundNo;
	};

	/**
	 * 옵션 수량 및 재고 체크하는 기능
	 * @param optNo
	 * @param cnt
	 * @returns {*}
	 */
	var checkOptionCount = function(optNo, cnt) {
		// 그냥 재고 체크
		if ( selected_options[optNo].use_stock && ! selected_options[optNo].stock_un_limit ) {
			if ( cnt > selected_options[optNo].stock ) {
				selected_options[optNo].count = selected_options[optNo].stock;
				return LOCALIZE.설명_현재재고부족으로N개이상구매할수없습니다(selected_options[optNo].stock+1);
			}
		}

		if ( selected_options[optNo].require ) { // 필수 옵션
			if ( is_digital_prod ) {
				// 만약 디지털 상품일 경우 수량변경이 제한된다.
				selected_options[optNo].count = 1;
				return LOCALIZE.설명_디지털상품은수량을변경하실수없습니다();
			}
		} else { // 선택 옵션
			if ( parseInt(selected_options[optNo].price) == 0 ) {
				// 가격이 0원일 경우... 0원 선택옵션 구매 시 최대 수량 제한 체크를 한다.
				switch( optional_limit_type ) {
					case 'limit':
						if ( cnt > optional_limit ) {
							selected_options[optNo].count = optional_limit;
							return LOCALIZE.설명_해당선택옵션은최대N개까지구매가능합니다(optional_limit);
						}
						break;
					case 'unique':
						if ( cnt > 1 ) {
							selected_options[optNo].count = 1;
							return LOCALIZE.설명_해당선택옵션은최대N개까지구매가능합니다(1);
						}
						break;
					case 'relative':
						var _order_cnt = order_count;
						if ( _order_cnt == 0 ) {
							$.each(selected_options, function(no, data){ if ( data.require ) { _order_cnt += data.count; } });
						}

						if ( cnt > _order_cnt ) {
							selected_options[optNo].count = _order_cnt;
							return LOCALIZE.설명_0원상품갯수제한();
						}
						break;
				}
			}
		}

		return true;
	};

	var increaseOptionCount = function(optNo, success){
		var $curCount = $('#prdOption' + optNo).find('input._count');
		var curCount = $curCount.val();
		if(isNaN(curCount))
			curCount = 1;
		else
			curCount = parseInt(curCount) + 1;

		var optional_result = checkOptionCount(optNo, curCount);
		if ( optional_result === true ) {
			selected_options[optNo].count = curCount;
		} else {
			alert(optional_result);
		}
		success();
	};

	var decreaseOptionCount = function(optNo, success){
		var $curCount = $('#prdOption' + optNo).find('input._count');
		var curCount = $curCount.val();
		if(isNaN(curCount))
			curCount = 1;
		else
			curCount = parseInt(curCount) - 1;
		if(curCount < 1) curCount = 1;

		var optional_result = checkOptionCount(optNo, curCount);
		if ( optional_result === true ) {
			selected_options[optNo].count = curCount;
		} else {
			alert(optional_result);
		}
		success();
	};

	var changeOptionCount = function(optNo, optCount, success){
		optCount = parseInt(optCount);
		if(isNaN(optCount)) optCount = 1;
		if(optCount < 1) optCount = 1;

		var optional_result = checkOptionCount(optNo, optCount);
		if ( optional_result === true ) {
			selected_options[optNo].count = optCount;
		} else {
			alert(optional_result);
		}

		success();
	};

	var checkProdStock = function(cnt) {
		// 그냥 재고 체크
		if ( prod_stock_use && ! prod_stock_unlimit ) {
			if ( cnt > prod_stock ) {
				order_count = prod_stock;
				return LOCALIZE.설명_현재재고부족으로N개이상구매할수없습니다(prod_stock+1);
			}
		}

		return true;
	};

	var increaseOrderCount = function(type, success){
		var o = $prod_detail.find('input._order_count_' + type);
		var curCount = o.val();
		if(isNaN(curCount))
			curCount = 1;
		else
			curCount = parseInt(curCount) + 1;

		var checkProdStockResult = checkProdStock(curCount);
		if ( checkProdStockResult === true ) {
			order_count = curCount;
		} else {
			alert(checkProdStockResult);
		}
		o.val(order_count);
		success();
	};

	var decreaseOrderCount = function(type, success){
		var o = $prod_detail.find('input._order_count_' + type);
		var curCount = o.val();
		if(isNaN(curCount))
			curCount = 1;
		else
			curCount = parseInt(curCount) - 1;

		if(curCount < 1) curCount = 1;

		var checkProdStockResult = checkProdStock(curCount);
		if ( checkProdStockResult === true ) {
			order_count = curCount;
		} else {
			alert(checkProdStockResult);
		}

		o.val(order_count);
		success();
	};

	var changeOrderCount = function(type, count, success, is_alert){
		if ( is_alert == void 0 ) is_alert = true;
		if(isNaN(count))
			count = 1;
		else
			count = parseInt(count);
		if(count < 1) count = 1;

		var checkProdStockResult = checkProdStock(count);
		if ( checkProdStockResult === true ) {
			order_count = count;
		} else {
			if ( is_alert ) alert(checkProdStockResult);
		}

		$prod_detail.find("input._order_count_" + type).val(order_count);
		success();
	};

	/**
	 * 옵션선택화면갱신
	 * type : prod (상품상세화면용), cart (장바구니 수량 변경용)
	 */
	var updateSelectedOptions = function(type){
		var html = '';
		var total_price_html = '';

		var total_price = 0;
		var total_count = 0;
		var option_total_price = 0;
		var period_discount_price = 0;

		if( require_option_count == 0 ) {
			total_count = order_count;
			total_price += order_count * prod_price;
			period_discount_price += calcPeriodDiscount(prod_price,order_count);
			if(period_discount_price > total_price) period_discount_price = total_price;

			// 필수옵션이 없을 때 기본 상품 수량 조절 html 출력
			if(!is_digital_prod){
				// 디지털 상품은 수량 변경 할 필요가 없으므로 출력하지 않는다
				switch(type){
					case 'prod':
						if( !is_view_price ) break;
						// 상품 상세페이지
						html += '<div class="opt_block no-border order_quantity_area" style="height: auto; "> ';
						html += '	<div class="area_tit holder">';
						html += '		<span class="option_title inline-blocked" style="margin-bottom: 7px">' + LOCALIZE.타이틀_수량() + '</span> ';
						html += '	</div> ';
						html += '	<div class="area_count holder">';
						html += '		<div class="option_btn_wrap" style="top:0;"> ';
						html += '			<div class="option_btn_tools" style="float: none;"> ';
						html += '				<a href="javascript:;" onclick="SITE_SHOP_DETAIL.decreaseOrderCount(\'mobile\', function(){SITE_SHOP_DETAIL.updateSelectedOptions(\'' + type + '\')})"><i class="btl bt-minus" aria-hidden="true"></i><span class="sr-only">minus</span></a>';
						html += '				<input type="text" title="number" value="' + order_count + '" class="form-control _order_count_mobile" onchange="SITE_SHOP_DETAIL.changeOrderCount(\'mobile\', $(this).val(), function(){SITE_SHOP_DETAIL.updateSelectedOptions(\'' + type + '\')})"> ';
						html += '				<a href="javascript:;" onclick="SITE_SHOP_DETAIL.increaseOrderCount(\'mobile\', function(){SITE_SHOP_DETAIL.updateSelectedOptions(\'' + type + '\')})"><i class="btl bt-plus" aria-hidden="true"></i><span class="sr-only">plus</span></a>';
						html += '			</div> ';
						html += '			<div class="area_price absolute absolute_right absolute_middle"><span>' + LOCALIZE.getCurrencyFormat(total_price) + '</span></div>';
						html += '		</div> ';
						html += '	</div> ';
						html += '</div> ';
						break;
					case 'cart':
						// 장바구니
						html += '<div class="opt_block hidden-lg hidden-sm hidden-md no-border" style="height: auto; "> ';
						html += '	<div class="col-control row"> ';
						html += '		<div class="col-xs-12"><span class="option_title text-bold inline-blocked" style="margin-bottom: 7px">' + LOCALIZE.타이틀_수량() + '</span></div> ';
						html += '		<div class="col-xs-12 option_btn_wrap" style="top:0;"> ';
						html += '			<div class="option_btn_tools" style="float: none;"> ';
						html += '				<a href="javascript:;" onclick="SITE_SHOP_CART.changeCartDecrease(\'mobile\')"><i class="btl bt-minus" aria-hidden="true"></i><span class="sr-only">minus</span></a>';
						html += '				<input type="text" title="number" value="' + order_count + '" class="form-control _order_count_mobile" onchange="SITE_SHOP_CART.changeCartOrderCount(\'mobile\', $(this).val())"> ';
						html += '				<a href="javascript:;" onclick="SITE_SHOP_CART.changeCartIncrease(\'mobile\')"><i class="btl bt-plus"></i><span class="sr-only">plus</span></a>';
						html += '			</div> ';
						html += '		</div> ';
						html += '	</div> ';
						html += '</div> ';
						break;
				}
			}
		}

		var require_class = '';
		var quantity_changeable = false;	// 상품 옵션부분 디지털 상품 및 선택옵션 구분 - 수량 변경 가능
		var option_price = 0;

		var prod_option_html = {"Y": "", "N": ""};
		var option_html = '';
		$.each(selected_options, function(no, data){
			total_count += parseInt(data.count);
			option_price = parseFloat(data.price || 0) * parseFloat(data.count);
			option_total_price += option_price;
			if(data.require == true){
				var option_period_discount_price = calcPeriodDiscount(data.price,data.count);
				if(option_period_discount_price > (option_price * data.count)) option_period_discount_price = option_price * data.count;
				period_discount_price += option_period_discount_price;
			}

			var html2 = [];
			$.each(data.options, function(no2, data2){
				html2.push(RemoveTag(data2.value_name));
			});
			quantity_changeable = !(is_digital_prod && data.require == true);
			require_class = (data.require ? '_selected_require_option ' : '');

			// 한 개마다 초기화
			option_html = '';
			if( type == 'prod') {
				// 상품 상세페이지
				option_html += '<div class="' + require_class + (no == 0 ? '' : ' middle ') + 'opt_block" id="prdOption' + no + '">';
				option_html += '	<div class="full-width opt_product_area">';
				option_html += '		<div class="area_tit holder">';
				option_html += '		 	<span class="body_font_color_80">' + html2.join(' / ') + '</span>';
				option_html += '			<a class="text-18 absolute absolute_right absolute_middle" href="javascript:;" onclick="SITE_SHOP_DETAIL.removeSelectedOption(' + no + ',\'prod\')"><i class="btl bt-times-circle"></i></a>';
				option_html += '		</div>';
				option_html += '		<div class="area_count holder">';
				option_html += '			<div class="option_btn_wrap">';
				if(quantity_changeable){
					option_html += '			<div class="option_btn_tools">';
					option_html += '				<a href="javascript:;" onclick="SITE_SHOP_DETAIL.decreaseOptionCount(' + no + ',\'prod\')"> <i class="btl bt-minus" aria-hidden="true"></i> </a>';
					option_html += '				<input type="text" value="' + data.count + '" class="form-control count _count" onchange="SITE_SHOP_DETAIL.changeOptionCount(' + no + ', $(this).val(),\'prod\')" />';
					option_html += '				<a href="javascript:;" onclick="SITE_SHOP_DETAIL.increaseOptionCount(' + no + ',\'prod\')"> <i class="btl bt-plus"></i> </a>';
					option_html += '			</div>';
				}
				option_html += '			</div>';
				option_html += '			<div class="area_price absolute absolute_right absolute_middle"><span>' + LOCALIZE.getCurrencyFormat(option_price) + '</span></div>';
				option_html += '		</div>';
				option_html += '	</div>';
				option_html += '</div>';
			} else {
				// 장바구니
				option_html += '<div class="' + require_class + (no == 0 ? '' : ' middle ') + 'opt_block" id="prdOption' + no + '">';
				option_html += '	<div class="tabled full-width text-13">';
				option_html += '		<div class="table-cell vertical-middle"><span class="body_font_color_80">' + html2.join(' / ') + '</span></div>';
				option_html += '			<div class="option_btn_wrap">';
				if(quantity_changeable){
					option_html += '			<div class="option_btn_tools">';
					option_html += '				<a href="javascript:;" onclick="SITE_SHOP_CART.changeCartItemDecrease(' + no + ')"> <i class="btl bt-minus" aria-hidden="true"></i> </a>';
					option_html += '				<input type="text" value="' + data.count + '" class="form-control count _count" onchange="SITE_SHOP_CART.changeCartItemCount(' + no + ', $(this).val())" />';
					option_html += '				<a href="javascript:;" onclick="SITE_SHOP_CART.changeCartItemIncrease(' + no + ')"> <i class="btl bt-plus"></i> </a>';
					option_html += '			</div>';
				}
				option_html += '			</div>';
				option_html += '		<div class="table-cell vertical-middle align_r width-3">';
				option_html += '			<span>' + LOCALIZE.getCurrencyFormat(option_price) + '</span>';
				option_html += '			<a href="javascript:;" onclick="SITE_SHOP_CART.changeCartItemRemove(' + no + ')"><i class="btl bt-times-circle"></i> </a>';
				option_html += '		</div>';
				option_html += '	</div>';
				option_html += '</div>';
			}

			// 기존처럼 옵션 구분 없이 선택 순 출력으로 사용하려면 prod_option_html 사용하는 부분 지우고 html += option_html 하면 됨
			prod_option_html[((data.require) ? "Y" : "N")] += option_html;
		});

		html += prod_option_html["Y"];
		html += prod_option_html["N"];

		total_price += option_total_price;

		// 선택된 옵션이 없으면 출력하지 않음
		if( total_count > 0 ){
			// 하단에 결제 예상 총 금액 표기
			switch(type){
				case 'prod':
					// 상품 상세페이지
					if( !is_view_price ) break;
					var membership_discount_html = '';
					var period_discount_html = '';

					var membership_discount_price = calcMembershipDiscount(total_price-period_discount_price);

					// PC 좁은화면 고려하여 user_agent가 아닌 브라우저 너비로 판단
					var is_mobile_view = $(window).width() < 768;
					var left_text_class = is_mobile_view ? '' : 'text-left';
					var total_price_class = is_mobile_view ? 'non_member ' : '';

					if(period_discount_price > 0) {
						// 상품 할인금액
						var discount_style = is_mobile_view ? 'padding-bottom: 4px;' : 'padding: 8px 0 12px';
						period_discount_html += '<p class="text-right">';
						period_discount_html += '	<span class="body_font_color_70 ' + left_text_class + '" style="' + discount_style + '">' + LOCALIZE.설명_상품할인금액() + '</span>';
						period_discount_html += '	<span class="period_discount_price" style="' + discount_style + '">' + LOCALIZE.getCurrencyFormat(period_discount_price) + '</span>';
						period_discount_html += '</p>';
						total_price = (total_price - period_discount_price);
					}

					if(membership_discount_price > 0){
						// 회원등급 할인
						var discount_style = is_mobile_view ? 'padding-bottom: 4px;' : 'padding: 0 0 16px 0';
						membership_discount_html += '<p class="text-right">';
						membership_discount_html += '	<span class="body_font_color_70 ' + left_text_class + '" style="' + discount_style + '">' + LOCALIZE.설명_회원등급할인() + '</span>';
						membership_discount_html += '	<span class="membership_discount_price" style="' + discount_style + '">' + LOCALIZE.getCurrencyFormat(membership_discount_price) + '</span>';
						membership_discount_html += '</p>';
						total_price = (total_price - membership_discount_price);
					}

					var discount_style = is_mobile_view ? 'padding-bottom: 4px;' : 'padding: 16px 0';
					total_price_html += '<div class="opt_block total bottom">';
					total_price_html += 	period_discount_html;
					total_price_html += 	membership_discount_html;
					if(!is_mobile_view && (period_discount_price > 0 || membership_discount_price > 0)){
						total_price_html += '<p style="border-bottom: 1px solid rgba(0, 0, 0, 0.1); position: absolute; width: 100%; display: block;"></p>';
					}
					total_price_html += '	<p class="no-margin text-right">';
					total_price_html += '		<span class="body_font_color_70 ' + left_text_class + '" style="' + discount_style + '">' + LOCALIZE.설명_결제예상금액임시(total_count) + '</span>';
					total_price_html += '		<span class="total_price ' + total_price_class +'" style="' + discount_style + '">' + LOCALIZE.getCurrencyFormat(total_price) + '</span>';
					total_price_html += '	</p>';
					total_price_html += '</div>';
					if($prod_detail.find('._footer_price_wrap').length > 0){
						var $footer_price_wrap = $prod_detail.find('._footer_price_wrap');
						$footer_price_wrap.find('._pay_label').text(LOCALIZE.설명_결제예상금액임시(total_count)).show();
						$footer_price_wrap.find('._pay_number').text(LOCALIZE.getCurrencyFormat(total_price)).show();
						$footer_price_wrap.find('._pay_org_label').hide();
						$footer_price_wrap.find('._pay_org_number').hide();
					}
					break;
				case 'cart':
					// 장바구니
					total_price_html += '<div class="opt_block row total bottom">';
					total_price_html += '	<div class="col-xs-4 vertical-middle"> <span class="body_font_color_70 text-13">' + LOCALIZE.타이틀_총수량(total_count) + '</span> </div>';
					total_price_html += '	<div class="col-xs-8 vertical-middle">';
					total_price_html += '		<p class="align_r body_font_color_50 text-13">' + total_price_localize_text + ' <span class="text-brand vertical-middle">' + LOCALIZE.getCurrencyFormat(total_price) + '</span></p>';
					total_price_html += '	</div>';
					total_price_html += '</div>';
					break;
			}
		}else{
			if(type === 'prod'){
				if($prod_detail.find('._footer_price_wrap').length > 0){
					// 선택된 옵션이 없을 경우 총 상품금액이 아닌 판매가 출력
					var $footer_price_wrap = $prod_detail.find('._footer_price_wrap');
					$footer_price_wrap.find('._pay_org_label').show();
					$footer_price_wrap.find('._pay_org_number').show();
					$footer_price_wrap.find('._pay_label').hide();
					$footer_price_wrap.find('._pay_number').hide();
				}
			}
		}

		html += total_price_html;
		$selected_options.html(html);
	};

	var membership_discount_data = {};
	var setMembershipSaleData = function(data){
		if( typeof data != 'undefined' ){
			membership_discount_data = data;
		}
	};

	var calcMembershipDiscount = function(price){
		var sale_price = 0;

		if( $.isEmptyObject(membership_discount_data) ) {
			return sale_price;
		}

		if( price >= membership_discount_data['minimum'] ) {
			sale_price = membership_discount_data['amount'];
			if ( membership_discount_data['type'] == 'percent' ) {
				sale_price = (price * membership_discount_data['amount']) / 100;
				if ( sale_price > membership_discount_data['maximum'] ) {
					sale_price = membership_discount_data['maximum'];
				}
			}
		}

		return sale_price > price ? price : sale_price;
	};

	var period_discount_data = {};
	var setPeriodDiscountData = function(flag,data,group_list){
		if( typeof data != 'undefined' ){
			period_discount_data = data;
			period_discount_data['switch'] = flag;
			period_discount_data['group_list'] = group_list;
		}
	};

	var calcPeriodDiscount = function(price,count){
		var sale_price = 0;
		var dc_price;
		if( $.isEmptyObject(period_discount_data) ) {
			return sale_price;
		}
		if(period_discount_data['switch'] === false || period_discount_data['switch'] == 'false') return sale_price;
		var group_list = period_discount_data['group_list'];

		for (var target in period_discount_data['target']){
			var discount_data = period_discount_data['target'][target];
			if(IS_GUEST){
				if(discount_data['group_type'] != 'guest') continue;
			}else{
				if(discount_data['group_type'] == 'group' && group_list.indexOf(discount_data['group_code']) == -1) continue;
			}
			if ( discount_data['dc_type'] == 'percent' ) {
				if(discount_data['dc_amount'] > 100) discount_data['dc_amount'] = 100;
				dc_price = (price * discount_data['dc_amount']) / 100 * count;
			}else{
				dc_price = discount_data['dc_amount'] * count;
			}
			if(sale_price < dc_price){
				sale_price = dc_price;
			}
		}

		return sale_price;
	};

	/**
	 * 장바구니에 추가
	 */
	var addCart = function(callback){
		$.ajax({
			type : 'POST',
			data : {'prodIdx' : current_prod_idx,
				'options' : selected_options,
				'orderCount' : order_count,
				'deliv_type' : $deliv_type,
				'deliv_pay_type': $deliv_pay_type
			},
			url : ('/shop/add_cart.cm'),
			dataType : 'json',
			success : function(result){
				if(result.msg == 'SUCCESS'){
					if ( typeof NP_LOG != 'undefined' ) NP_LOG.AddToCart();
					if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.AddToCart(result.prod_id,result.cart_price,result.currency,result.prod_count,result.total_price,result.fb_event_id,result.fb_external_id);
					if ( typeof ACE_COUNTER != 'undefined' ) ACE_COUNTER.AddToCart(result.prod_id, result.prod_name, result.prod_count);
					if ( typeof CHANNEL_PLUGIN != 'undefined') CHANNEL_PLUGIN.AddToCart(result.prod_id, result.prod_count,result.cart_price, result.currency);
					if ( typeof ACE_COUNTER_PARTNER != 'undefined' ) ACE_COUNTER_PARTNER.AddToCart(result.prod_id, result.prod_count, result.check_quantity);
					if ( typeof AW_PRODUCT != 'undefined' ) AW_PRODUCT(result.prod_count);
					if ( typeof AM_PRODUCT != 'undefined' ) AM_PRODUCT(result.prod_count);
					if(result.advanced_trace_data != null){
						if(result.advanced_trace_data.header != ''){
							$('head').append(result.advanced_trace_data.header);
						}
						if(result.advanced_trace_data.body != ''){
							$('body').append(result.advanced_trace_data.body);
						}
						if(result.advanced_trace_data.footer != ''){
							$('footer').append(result.advanced_trace_data.footer);
						}
					}

					// 같은 상품이 장바구니에 존재하지 않을 경우 장바구니 카운트 증가
					if(!result.prod_found){
						var $shop_cart_badge_cnt_wrap = $('._shop_cart_badge_cnt_wrap');
						var org_cnt = 0;
						if($shop_cart_badge_cnt_wrap.eq(0).text() != '') org_cnt = parseInt($shop_cart_badge_cnt_wrap.eq(0).text());
						$shop_cart_badge_cnt_wrap.text(org_cnt + 1);
					}

					callback();
				}else{
					alert(result.msg);
				}
			}
		});
	};

	/**
	 * 바로 주문하기  추가
	 */
	var addOrder = function(type, backurl, callback){
		add_order_progress_check = true;

		var _data = {
			'backurl' : backurl,
			'prodIdx' : current_prod_idx,
			'optDataList' : selected_options,
			'orderCount' : order_count,
			'type' : type,
			'deliv_type' : $deliv_type,
			'deliv_pay_type': $deliv_pay_type,
			'deliv_country' : $deliv_country
		};

		_data.ace_pid = window._AcePID || window._AceMID;

		$.ajax({
			type : 'POST',
			data : _data,
			url : ('/shop/add_order.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					if ( typeof AW_PRODUCT != 'undefined' && typeof AW_F_D != 'undefined'){
						AW_F_D(current_prod_idx,'i',order_count);
						AW_PRODUCT(order_count);
					}
					callback(result);
				}else{
					add_order_progress_check = false;

					if ( type == 'talkpay' ) {
						callback(result);
					} else {
						alert(result.msg);
					}
				}
			}
		});
	};
	/**
	 * 네이버페이지 찜하기  등록
	 */
	var addNPayWish = function(){
		$.ajax({
			type : 'POST',
			data : {'prod_idx_list' : [current_prod_idx]},
			url : ('/shop/add_npay_wish.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					if(result.mobile == 'Y')
						window.location.href = result.npay_url;
					else
						window.open(result.npay_url, "", "scrollbars=yes,width=400,height=267");
				}else{
					alert(LOCALIZE.설명_네이버페이찜등록실패(escape_javascript(result.msg)));
				}
			}
		});
	};


	var digitalFileDownload = function(order_no){
		if ( !is_digital_prod ) return false;
		$.ajax({
			"type": "POST",
			"data": {"prod_no": current_prod_idx, "order_no": order_no, "mode": "detail"},
			"url": "/ajax/shop_digital_prod_download.cm",
			"dataType": "JSON",
			"success": function(res){
				if(res['msg'] == 'SUCCESS'){
					if( res['download_info_msg'].trim() == '' || confirm(res['download_info_msg']) ){
						location.href = res['file_url'];
					}
				}else{
					alert(res['msg']);
					if ( typeof res['reload'] != 'undefined' ) {
						location.reload();
					}
				}
			}
		});
	};
	/**
	 * 모바일에서 구매하기 클릭시 옵션 표시 처리
	 */
	var showMobileOptions = function(){
		$prod_detail.addClass('open');
		showMobileFirstSelect();
		var $form = $options.find('._form_parent');
		$form.nextAll().toggleClass('disabled', true);
	};

	/**
	 * 조합형 + 필수 옵션일 때 첫번째 셀렉트 박스는 show 처리
	 */
	var showMobileFirstSelect = function(){
		var $form_select = $options.find('._first_form_select');
		var current = 0;

		$form_select.eq(current).find('.dropdown-toggle').attr('aria-expanded', true);
		$form_select.eq(current).find('.dropdown-toggle').toggleClass('active', true);
		$form_select.eq(current).attr('data-val', true);

		setTimeout(function(){
			$form_select.eq(current).find('.dropdown-toggle').dropdown('toggle');
		},50);

		$form_select.eq(current).find('.dropdown-toggle').off('click').on('click', function(){
			$form_select.eq(current).find('.dropdown-toggle').attr('aria-expanded', false);
			$form_select.eq(current).find('.dropdown-toggle').toggleClass('active', false);
			$form_select.eq(current).attr('data-val', false);
		});
		$form_select.parent('._form_parent').nextAll().toggleClass('disabled', true);
	};

	/**
	 * 모바일에서 구매하기 클릭시 옵션 숨기기 처리
	 */
	var hideMobileOptions = function(){
		$prod_detail.removeClass('open');
	};

	/**
	 * PC B타입에서 구매하기/장바구니 클릭 시 옵션 표시 처리, 옵션 표시되어있으면 구매하기/장바구니
	 * @param type order: 구매하기, cart: 장바구니
	 * @param backurl 비회원 구매 시 로그인 등에서 사용될 back url
	 */
	var showPCOptions = function(type, backurl){
		if(backurl == undefined) backurl = location.href;
		if($prod_detail.hasClass('open')){
			if(type == 'cart'){
				SITE_SHOP_DETAIL.addCart();
			}else{
				SITE_SHOP_DETAIL.addOrder('normal', backurl);
			}
		}else{
			$prod_detail.find('._btn_npay').hide();
			$prod_detail.find('._btn_kakaopay').hide();
			$prod_detail.addClass('open');
		}
	};

	var hidePCOptions = function(){
		$prod_detail.removeClass('open');
		$prod_detail.find('._btn_npay').show();
		$prod_detail.find('._btn_kakaopay').show();
	};

	/**
	 * qna 상세페이지 모달
	 * @param idx
	 * @param qna_page
	 * @param is_hash		주소창에 직접 입력 또는 최신글 위젯 등 상세페이지 외부에서 바로 넘어왔는지 여부, Y/N
	 */
	var viewQnaDetail = function(idx,qna_page,is_hash){
		$(function(){
			$.ajax({
				type : 'POST',
				data : {idx : idx, qna_page : qna_page},
				url : ('/ajax/qna_detail_view.cm'),
				dataType : 'json',
				async : false,
				cache : false,
				success : function(res){
					if(res.msg === 'SUCCESS'){
						$.cocoaDialog.open({
							type : 'prod_detail', custom_popup : res.html, width : 800});
						if(checkUseHistory()){
							// 모달 히스토리 커스텀(IE 10 이상)
							var current_url = location.href.indexOf('#') === -1 ? location.href : location.href.substr(0, location.href.indexOf('#'));
							var back_url = document.referrer.indexOf('#') === -1 ? document.referrer : document.referrer.substr(0, document.referrer.indexOf('#'));
							if(current_url !== back_url && is_hash !== 'Y'){
								history.pushState(null, null, current_url);
							}
							history.replaceState(null, null, current_url + "#qna_detail!/" + res.idx);
						}else{
							location.hash = "qna_detail!/" + res.idx;
						}
						$(window).off('hashchange').on('hashchange',function(){
							var hash_qna_spilt = location.hash.split('!/')[1];
							if(!hash_qna_spilt){
								$.cocoaDialog.close();
							}else{
								if(checkUseHistory()){
									var hash_spilt_tab = location.hash.split('!/')[0];
									if(hash_spilt_tab === '#review_detail'){
										viewReviewDetail(hash_qna_spilt);
									}else if(hash_spilt_tab === '#qna_detail'){
										viewQnaDetail(hash_qna_spilt);
									}
								}
							}
						});
						$('.modal_prod_detail').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
							if(is_hash !== 'Y'){
								removeQnawHash();
							}
						});
					}else{
						alert(res.msg);
					}
				}
			});
		});
	};

	var removeQnawHash = function(){
		$(window).off('hashchange');
		var hash_qna_spilt = location.hash.split('!/')[1];
		if(hash_qna_spilt){
			if(checkUseHistory()){
				history.back();
			}else{
				location.href = '#qna_detail';
			}
		}
	};

	/**
	 *  리뷰 모달 html 변경 (앞 뒤 버튼으로 호출)
	 * @param idx
	 */
	var changeReviewDetail = function(idx){
		$.ajax({
			type: 'POST',
			data: {idx:idx, review_page: 1, only_photo : "Y"},
			url: ('/ajax/review_detail_change.cm'),
			dataType: 'json',
			async: false,
			cache: false,
			success: function (res) {
				if(res.msg === 'SUCCESS'){
					$('._review_modal_body').html(res.html);
					if(IS_MOBILE){
						// setReviewSwipe(res.prev_idx, res.next_idx);
						$body.find('.modal.review').after(res.btn_html);
						$body.find('._btn_nav_wrap').off('click').on('click', function(){
							$(this).remove();
						});
						$body.find('#review_detail_close').off('click').on('click', function(){
							$('._btn_nav_wrap').remove();
						});
						setReviewBtnAnimation();
						setReviewClass(idx);
					}
					setReviewCarousel();
					$(window).resize(function(){
						setReviewCarousel();
					});
					if(res.review_code != '') SHOP_REVIEW_COMMENT.getReviewCommentHtml(res.review_code,'modal');
				}else{
					alert(res.msg);
				}
			}
		});
	};

	/**
	 * 리뷰 상세페이지 모달
	 * @param idx
	 * @param review_page
	 * @param only_photo
	 * @param is_hash		주소창에 직접 입력 또는 최신글 위젯 등 상세페이지 외부에서 바로 넘어왔는지 여부, Y/N
	 */
	var viewReviewDetail = function(idx,review_page,only_photo,is_hash){
		$(function(){
			$.ajax({
				type: 'POST',
				data: {idx:idx, review_page: review_page, only_photo : only_photo},
				url: ('/ajax/review_detail_view.cm'),
				dataType: 'json',
				async: false,
				cache: false,
				success: function (res) {
					if(res.msg === 'SUCCESS'){
						$.cocoaDialog.open({type : 'prod_detail review', custom_popup : res.html, width : 800});
						if(checkUseHistory()){
							// 모달 히스토리 커스텀(IE 10 이상)
							var current_url = location.href.indexOf('#') === -1 ? location.href : location.href.substr(0, location.href.indexOf('#'));
							var back_url = document.referrer.indexOf('#') === -1 ? document.referrer : document.referrer.substr(0, document.referrer.indexOf('#'));
							if(current_url !== back_url && is_hash !== 'Y'){
								history.pushState(null, null, current_url);
							}
							history.replaceState(null, null, current_url + "#review_detail!/" + res.idx);
						}else{
							location.hash = "review_detail!/" + res.idx;
						}
						$(window).off('hashchange').on('hashchange',function(){
							var hash_review_spilt = location.hash.split('!/')[1];
							if(!hash_review_spilt){
								$.cocoaDialog.close();
							}else{
								if(checkUseHistory()){
									var hash_spilt_tab = location.hash.split('!/')[0];
									if(hash_spilt_tab === '#qna_detail'){
										viewQnaDetail(hash_review_spilt);
									}else if(hash_spilt_tab === '#review_detail'){
										viewReviewDetail(hash_review_spilt);
									}
								}
							}
						});
						$('.modal_prod_detail').off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
							if(is_hash !== 'Y'){
								removeReviewHash();
							}
						});
					}else{
						alert(res.msg);
					}
				}
			});
		});
	};

	var removeReviewHash = function(){
		$(window).off('hashchange');
		var hash_review_spilt = location.hash.split('!/')[1];
		if(hash_review_spilt){
			if(checkUseHistory()){
				history.back();
			}else{
				location.href = '#review_detail';
			}
		}
	};



	/**
	 *
	 */
	var getOnlyPhotoReview = function(only_photo_switch,is_mobile,is_one_page){
		if(is_mobile === 'Y'){
			var ajax_target = '/shop/prod_review_mobile_html.cm';
			var $target_html = $prod_detail_content_mobile;
		}else{
			var ajax_target = '/shop/prod_review_pc_html.cm';
			if(is_one_page === 'Y'){
				var $target_html = $('._review_wrap');
			}else{
				var $target_html = $prod_detail_content_pc;
			}
		}
		if ( $target_html.length ) {
			var $icon_picture = $('.icon-picture');
			$.ajax({
				type : 'POST',
				data : {'prod_idx' : current_prod_idx, 'only_photo' : only_photo_switch},
				url : (ajax_target),
				dataType : 'html',
				cache : false,
				async : false,
				success : function(result){
					$target_html.html(result);
					if($icon_picture.hasClass('active')){
						$icon_picture.removeClass('active');
					}else{
						$icon_picture.addClass('active');
					}
				}
			});
		}
	};

	var getFirstPhotoReview = function(page){
		if ( $first_photo_review_wrap.length ) {
			var is_cache = false;
			var callback = function(result){
				$first_photo_review_wrap.html(result);
				if ( ! is_cache ) IMWEB_SESSIONSTORAGE.set("PROD_REVIEW_PC_PHOTO_" + current_prod_idx + "_" + page, result.replace(/\t+/g, '').trim(), 60);
			};

			var html = IMWEB_SESSIONSTORAGE.get("PROD_REVIEW_PC_PHOTO_" + current_prod_idx + "_" + page);
			if ( html ) {
				is_cache = true;
				callback(html);
			} else {
				$.ajax({
					type : 'POST',
					data : {prod_idx : current_prod_idx, page: page},
					url : '/shop/prod_photo_review_pc_html.cm',
					dataType : 'html',
					cache : false,
					async : false,
					success : callback
				});
			}
		}
	};

	var viewPhotoReviewMore = function(is_mobile, is_one_page){
		if(is_mobile !== 'Y'){
			// 모바일에서는 구매평 내부에서만 호출하므로 탭 전환 필요 없음
			changeContentPCTab(CONST.TAB_TYPE.REVIEW, 0, 0, false);
		}
		getOnlyPhotoReview('Y', is_mobile, is_one_page);
		if(is_mobile !== 'Y'){
			if(is_one_page === 'Y'){
				$('._detail_review_wrap')[0].scrollIntoView();
			}else{
				$('._prod_detail_detail_lazy_load')[0].scrollIntoView();
			}
		}else{
			$('._prod_detail_detail_lazy_load_mobile')[0].scrollIntoView();
		}
	};

	var getReviewSummary = function(){
		if ( $review_summary_wrap_mobile.length ) {
			$.ajax({
				type: 'GET',
				data: {'prod_idx': current_prod_idx, 'type': 'mobile'},
				url: ('/shop/prod_review_summary_html.cm'),
				dataType: 'html',
				async: false,
				success : function(result){
					$review_summary_wrap_mobile.html(result);
					startReviewImageRolling('mobile');
				}
			});
		}

		if(!IS_MOBILE){
			if ( $review_summary_wrap.length ) {
				var pc_type = $prod_detail.width() > 991 ? 'pc' : 'tablet';

				$.ajax({
					type : 'GET',
					data : {'prod_idx' : current_prod_idx, 'type' : pc_type},
					url : ('/shop/prod_review_summary_html.cm'),
					dataType : 'html',
					async : false,
					success : function(result){
						$review_summary_wrap.html(result);
						if($prod_detail.width() <= 991){
							$review_summary_wrap.removeClass('review_summary_wrap');
							$review_summary_wrap.addClass('review_summary_wrap_tablet');
						}
						$review_summary_wrap.show();
						startReviewImageRolling(pc_type);
					}
				});
			}
		}
	};

	var startReviewImageRolling = function(type){
		if(type === 'mobile'){
			$review_image_list = $review_summary_wrap_mobile.find('.review_image_list');
		}else{
			$review_image_list = $review_summary_wrap.find('.review_image_list');
		}
		$review_image_list_rolling = $review_image_list.find('div.owl-carousel');

		var navtext_left = $('<i class="btl bt-angle-left" style="position: absolute; font-size: 20px; left: -25px; top: 45px;"></i>');
		var navtext_right = $('<i class="btl bt-angle-right" style="position: absolute; font-size: 20px; right: -25px; top: 45px;"></i>');

		var margin = type === 'mobile' ? 5 : 10;
		var items = 4;


		var img_width = Math.floor(($review_image_list.innerWidth() - (margin * (items - 1))) / items);
		if(type === 'tablet'){
			if(img_width > 150){
				// img_width가 150보다 크면 개수 자체를 150에 맞춰서 재계산
				items = Math.ceil($review_image_list.innerWidth() / (150 + margin / 2));
				img_width = Math.floor(($review_image_list.innerWidth() - (margin * (items - 1))) / items);
			}
		}
		$('._review_carousel_image').each(function(){
			$(this).css('width', img_width);
			$(this).css('height', img_width);
		});

		$review_image_list_rolling.owlCarousel({
			dots:false,
			nav: type === 'pc',
			navText: [navtext_left, navtext_right],
			slideSpeed : 300,
			paginationSpeed : 400,
			animateOut : 'fadeOut',
			autoWidth: true,
			items : items,
			margin: margin
		});
	};

	/**
	 *  pc 상세페이지 탭 이벤트 처리
	 * @param type 탭 type
	 * @param r_p
	 * @param q_p
	 * @param paging_on 스크롤 조정
	 * @param only_photo
	 * @param shop_view_body_width
	 */
	var changeContentPCTab = function(type, r_p, q_p, paging_on,only_photo,shop_view_body_width){
		var $site_prod_nav_wrap = $('.site_prod_nav_wrap');
		if(current_content_tab != ''){
			$site_prod_nav_wrap.find('a._' + current_content_tab).removeClass('active');
		}
		current_content_tab = type;
		$site_prod_nav_wrap.find('a._' + type).addClass('active');
		switch(type){
			case CONST.TAB_TYPE.DETAIL :
				if($review_summary_wrap.html()){
					$review_summary_wrap.empty();
					$review_summary_wrap.hide();
				}

				var pc_detail_html = IMWEB_TEMPLATE.loadSimple("prodDetailPC");
				$prod_detail_content_pc.html(pc_detail_html);
				if( typeof shop_view_body_width != 'undefined' ) {
					$prod_detail_content_pc.css({'width' : shop_view_body_width + '%'});
				}

				if ( use_lazy_load ) {
					runLazyload();
				} else {
					if(paging_on) document.getElementById("pc_tab_offset").scrollIntoView();
				}

				$body.find('._seemore_wrap').show();
				$prod_detail_content_pc.toggleClass('hide_seemore', false);
				break;
			case CONST.TAB_TYPE.REVIEW :
				if(!$review_summary_wrap.html()){
					getReviewSummary();
				}
				if(pc_tab_type == 'Y') $prod_detail_content_pc = $('._review_wrap');
				$.ajax({
					type : 'POST',
					data : {'prod_idx' : current_prod_idx, 'review_page' : r_p, 'qna_page' : q_p, 'only_photo' : only_photo},
					url : ('/shop/prod_review_pc_html.cm'),
					dataType : 'html',
					cache : false,
					async: false,
					success : function(result){
						$prod_detail_content_pc.html(result);
						$prod_detail_content_pc.css({'width': '100%'});
						if(paging_on) document.getElementById("pc_tab_offset").scrollIntoView();
						$body.find('._seemore_wrap').hide();
						$prod_detail_content_pc.toggleClass('hide_seemore', true);
					}
				});
				break;
			case CONST.TAB_TYPE.QNA :
				if($review_summary_wrap.html()){
					$review_summary_wrap.html('');
					$review_summary_wrap.hide();
				}
				if(pc_tab_type == 'Y') $prod_detail_content_pc = $('._qna_wrap');
				$.ajax({
					type : 'POST',
					data : {'prod_idx' : current_prod_idx, 'review_page' : r_p, 'qna_page' : q_p},
					url : ('/shop/prod_qna_pc_html.cm'),
					dataType : 'html',
					cache : false,
					async: false,
					success : function(result){
						$prod_detail_content_pc.html(result);
						$prod_detail_content_pc.css({'width': '100%'});
						if(paging_on) document.getElementById("pc_tab_offset").scrollIntoView();
						$body.find('._seemore_wrap').hide();
						$prod_detail_content_pc.toggleClass('hide_seemore', true);
					}
				});
				break;
			case CONST.TAB_TYPE.RETURN:
				if ( is_init_detail ) {
					var $_target = ( options.shop_pc_tab_type_one_page == 'Y' ? $prod_detail_return : $prod_detail_content_pc );
					$_target.empty();
					if ( last_refund_data ) {
						var prod_detail_return_html = IMWEB_TEMPLATE.loadSimple("prodReturnPc", last_refund_data);
						$_target.html(prod_detail_return_html);
						$body.find('._seemore_wrap').hide();
						$_target.toggleClass('hide_seemore', true);
					}
				}
				break;
		}
		setTabHistory(type);
	};

	/**
	 * PC 펼침 탭일 때도 스크롤 후 히스토리 커스텀 처리
	 * @param type
	 */
	var scrollPCTab = function(type){
		switch(type){
			case CONST.TAB_TYPE.DETAIL:
				document.getElementsByClassName("detail_detail_wrap")[0].scrollIntoView();
				break;
			case CONST.TAB_TYPE.REVIEW:
				document.getElementsByClassName("detail_review_wrap")[0].scrollIntoView();
				break;
			case CONST.TAB_TYPE.QNA:
				document.getElementsByClassName("detail_qna_wrap")[0].scrollIntoView();
				break;
			case CONST.TAB_TYPE.RETURN:
				document.getElementsByClassName("detail_return_wrap")[0].scrollIntoView();
				break;
		}
		setTabHistory(type);
	};

	/**
	 * 상세정보 탭 변경 처리
	 * @param type
	 * @param r_p
	 * @param q_p
	 * @param only_photo
	 * @param paging_on
	 */
	var changeContentTab = function(type, r_p, q_p, paging_on,only_photo){
		if(current_content_tab != ''){
			$prod_detail_content_tab_mobile.find('a._' + current_content_tab).removeClass('active');
		}
		current_content_tab = type;
		$prod_detail_content_tab_mobile.find('a._' + type).addClass('active');

		$prod_detail_content_tab_mobile.find('a').on('click',function(){
			$('#tab_offset')[0].scrollIntoView();
		});

		switch( type ) {
			case CONST.TAB_TYPE.DETAIL:
				$review_summary_wrap_mobile.html('');

				var mobile_detail_html = IMWEB_TEMPLATE.loadSimple('prodDetailMobile');
				if( mobile_detail_html ) {
					if(use_lazy_load){
						$prod_detail_content_mobile.html(mobile_detail_html);
						$prod_detail_content_mobile.find('img').addClass('doz_lazyloading').lazyload({		/* 상품 상세페이지 lazy load 적용, 기본 /img/no-image.png 는 한번만 불러옴 */
							placeholder : NO_IMAGE_URL,
							threshold : 100,
							effect : "fadeIn",
							load : function(){
								$(this).removeAttr('height');
								$(this).removeClass('doz_lazyloading');
							}
						});
					}else{
						$prod_detail_content_mobile.html(mobile_detail_html);
					}
					if(IS_MOBILE){
						loadDelivSetting(current_prod_idx, $('#prod_deliv_setting .countryList').val(), $('#deliv_type').val(), $('#deliv_pay_type').val());
					}
				} else {
					$prod_detail_content_mobile.html('<div style="text-align: center; padding: 50px 0;"><div class="body_font_color_40" style="font-size: 18px; margin:30px"><div> </div>');
				}
				setMobileVideoRatio();

				$body.find('._seemore_wrap').show();
				$prod_detail_content_mobile.toggleClass('hide_seemore', false);
				$prod_detail_content_mobile.toggleClass('product_detail_mobile', true);
				$prod_detail_content_mobile.toggleClass('product_review_mobile', false);
				$prod_detail_content_mobile.toggleClass('product_qna_mobile', false);
				$prod_detail_content_mobile.toggleClass('product_return_mobile', false);
				break;
			case CONST.TAB_TYPE.REVIEW:
				r_p = r_p || 0;
				q_p = q_p || 0;

				var is_method_get = ( r_p == 0 && q_p == 0 );

				$.ajax({
					type : ( is_method_get ? 'GET' : 'POST' ),
					data : {'prod_idx' : current_prod_idx, 'review_page' : r_p, 'qna_page' : q_p, 'only_photo' : only_photo, 'member_hash': MEMBER_HASH},
					url : ('/shop/prod_review_mobile_html.cm'),
					dataType : 'html',
					async: false,
					success : function(result){
						if(!$review_summary_wrap_mobile.html()){
							getReviewSummary();
						}
						$prod_detail_content_mobile.html(result);
						if(paging_on) document.getElementById("prod_detail_content_tab_mobile").scrollIntoView();
						$body.find('._seemore_wrap').hide();
						$prod_detail_content_mobile.toggleClass('hide_seemore', true);
						$prod_detail_content_mobile.toggleClass('product_detail_mobile', false);
						$prod_detail_content_mobile.toggleClass('product_review_mobile', true);
						$prod_detail_content_mobile.toggleClass('product_qna_mobile', false);
						$prod_detail_content_mobile.toggleClass('product_return_mobile', false);
					}
				});
				break;
			case CONST.TAB_TYPE.QNA:
				$.ajax({
					type : 'POST',
					data : {'prod_idx' : current_prod_idx, 'review_page' : r_p, 'qna_page' : q_p},
					url : ('/shop/prod_qna_mobile_html.cm'),
					dataType : 'html',
					cache : false,
					async: false,
					success : function(result){
						if($review_summary_wrap_mobile.html()){
							$review_summary_wrap_mobile.html('');
						}
						$prod_detail_content_mobile.html(result);
						$body.find('._seemore_wrap').hide();
						$prod_detail_content_mobile.toggleClass('hide_seemore', true);
						$prod_detail_content_mobile.toggleClass('product_detail_mobile', false);
						$prod_detail_content_mobile.toggleClass('product_review_mobile', false);
						$prod_detail_content_mobile.toggleClass('product_qna_mobile', true);
						$prod_detail_content_mobile.toggleClass('product_return_mobile', false);
					}
				});
				break;
			case CONST.TAB_TYPE.RETURN:
				$prod_detail_content_mobile.empty();
				if ( is_init_detail && last_refund_data ) {
					var prod_detail_return_html = IMWEB_TEMPLATE.loadSimple("prodReturnMobile", last_refund_data);
					$prod_detail_content_mobile.html(prod_detail_return_html);
					$body.find('._seemore_wrap').hide();
				}
				$prod_detail_content_mobile.toggleClass('hide_seemore', true);
				$prod_detail_content_mobile.toggleClass('product_detail_mobile', false);
				$prod_detail_content_mobile.toggleClass('product_review_mobile', false);
				$prod_detail_content_mobile.toggleClass('product_qna_mobile', false);
				$prod_detail_content_mobile.toggleClass('product_return_mobile', true);
				break;
		}
		setTabHistory(type);
	};

	var setTabHistory = function(type){
		if(!$('body').hasClass('admin')){
			if(checkUseHistory()){
				var current_url = location.href.indexOf('#') === -1 ? location.href : location.href.substr(0, location.href.indexOf('#'));
				switch(type){
					case CONST.TAB_TYPE.DETAIL:
						if(location.href.indexOf('#') !== -1){
							history.replaceState(null, null, current_url + "#prod_detail_detail");
						}
						break;
					case CONST.TAB_TYPE.REVIEW:
						history.replaceState(null, null, current_url + "#review_detail");
						break;
					case CONST.TAB_TYPE.QNA:
						history.replaceState(null, null, current_url + "#qna_detail");
						break;
				}
			}else{
				switch(type){
					case CONST.TAB_TYPE.DETAIL:
						if(location.href.indexOf('#') !== -1){
							location.hash = "prod_detail_detail";
						}
						break;
					case CONST.TAB_TYPE.REVIEW:
						location.hash = "review_detail";
						break;
					case CONST.TAB_TYPE.QNA:
						location.hash = "qna_detail";
						break;
				}
			}
		}
	};

	var setMobileVideoRatio = function(){
		// 동영상 크기 값이 입력되어 있을 시 모바일에서도 입력된 크기 기준으로 비율 계산해서 반영
		var $fr_video = $prod_detail_content_mobile.find('.fr-video.fr-dvb');
		if($fr_video.length > 0){
			$fr_video.each(function(k, v){
				var $fr_video_iframe = $(v).find('iframe');
				if($fr_video_iframe.length > 0){
					var origin_width = $fr_video_iframe[0].style.width;
					var origin_height = $fr_video_iframe[0].style.height;
					if(origin_width != '' && origin_height != ''){
						// px 단위일 때만 비율 보정
						var origin_width_split = origin_width.split('px');
						var origin_height_split = origin_height.split('px');
						if(origin_width_split.length === 2 && origin_height_split.length === 2){
							var video_ratio = (origin_height_split[0] / origin_width_split[0]) * 100;
							$(v).css('padding-bottom', video_ratio + '%');
						}
					}
				}
			});
		}
	};

	var hideAddCartAlarm = function(){
		$add_cart_alarm.hide();
	};

	var changeInput = function(){
		$(document).on('keypress', 'input._requireInputOption', function (e) {
			if (e.which == 13) {
				e.preventDefault();
				var $next = $('[tabIndex=' + (+this.tabIndex + 1) + ']');
				// if (!$next.length) {}
				$next.focus() .click();
			}
		});
	};

	var changeTab = function(type){
		$('._detail_detail_wrap').hide();
		$('._detail_review_wrap').hide();
		$('._detail_qna_wrap').hide();
		$('._detail_'+type+'_wrap').show();
	};

	var countryCodeChange = function(country){
		$.ajax({
			type : 'POST',
			data : {'country':country},
			url : ('/shop/country_code_change.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg != "SUCCESS"){
					alert(result.msg);
				}
			}
		});
	};

	var DetailItemMake = function(idx, change_country, deliv_type, deliv_pay_type){
		var is_design_mode = ( location.pathname.indexOf('/admin/design/') != -1 );
		$deliv_country = change_country;
		$deliv_pay_type = deliv_pay_type;
		$.ajax({
			type : 'GET',
			data : {'idx' : idx,'change_country':change_country,'deliv_type':deliv_type,'deliv_pay_type':deliv_pay_type, '__': MEMBER_HASH},
			url : ('/shop/prod_detail_make.cm'),
			dataType : 'json',
			cache : !is_design_mode,
			success : function(result){
				if ( result.msg == 'SUCCESS' ) {
					$prod_detail.find('._item_detail_wrap').html(result.html);

					/* 환불 정보 입력 */
					if ( result.refund ) {
						last_refund_data = result.refund;
						var prod_detail_return_html = IMWEB_TEMPLATE.loadSimple("prodReturnPc", last_refund_data);

						if ( current_content_tab == CONST.TAB_TYPE.RETURN && options.shop_pc_tab_type_one_page == 'N' ) {
							changeContentPCTab(current_content_tab);
						} else {
							$prod_detail_return.html(prod_detail_return_html);
						}
					}

					if ( typeof naver != typeof void 0 && !! naver.NaverPayButton ) {
						makeNaverPayBtn('naverPayWrap', change_country == 'KR' || change_country == 'none' || change_country == '' );
					}

					if ( typeof kakaoCheckout != 'undefined' && typeof makeTalkPayButton != 'undefined') {
						makeTalkPayButton('create-kakao-checkout-button', ( change_country == 'KR' || change_country == 'none' || change_country == '' ));
					}
				}
			}
		});
	};

	/**
	 * 모바일 옵션 input클릭시 입력키보드 때문에 css예외처리가 필요하여 클래스 추가.
	 */
	var addEventMobileOptionInput = function(){
		$options.find('input.form-control').focus(function(){
			if(!$('body').hasClass('mobile_focus_on')){
				$('body').addClass('mobile_focus_on');
			}
		});
		$options.find('input').blur(function(){
			if($('body').hasClass('mobile_focus_on')){
				$('body').removeClass('mobile_focus_on');
			}
		});
	};

	/**
	 * 쇼핑 카테고리 목록에서 상품 상세페이지 레이어팝업 띄우는 함수
	 */
	var openProdDetailFromShoppingList = function(idx, back_url, is_prod_detail_page, is_mobile, prod_idx_org){
		$.ajax({
			type : 'GET',
			data : {'idx' : idx, 'is_prod_detail_page': is_prod_detail_page},
			url : ('/shop/prod_detail_from_shopping_list.cm'),
			dataType : 'html',
			cache: true,
			async : false,
			success : function(result){
				$.cocoaDialog.open({type : 'prod_detail_from_shopping_list', custom_popup : result, 'close_block' : false});
				$('.modal_prod_detail_from_shopping_list').addClass('custom_tooltip');

				if(is_prod_detail_page == 'Y' && is_mobile){
					var $mobile_action_btn_wrap = $('._mobile_action_btn_wrap');
					$mobile_action_btn_wrap.hide();
				}

				$('.modal_prod_detail_from_shopping_list').on('hidden.bs.modal', function (e) {
					$('.modal_prod_detail_from_shopping_list').find('.modal-content').html(''); // close 해도 html 이 남아있어서 스크립트가 재실행이 안 되므로 html 을 삭제시킴

					if(is_prod_detail_page == 'Y'){
						initDetail({
							prod_idx: prod_idx_org,
							prod_price: prod_price_org,
							require_option_count: require_option_count_org,
							shop_use_full_load: use_lazy_load_org,
							shop_pc_tab_type_one_page: pc_tab_type_org,
							is_site_page: is_site_page_org,
							is_disital: is_digital_prod_org,
							is_prod_detail_page: false,
							is_price_view_permission: is_view_price,
							cm_style: cm_data,
							section_code: section_code
						});

						if(is_mobile) $mobile_action_btn_wrap.show();
					}
				});

				if(checkUseHistory()){ // 뒤로가기 누를 경우 모달만 꺼지고 현재 페이지에 남아있도록
					history.pushState(null, null, location.href);
					window.onpopstate = function () {
						if($('.modal_prod_detail_from_shopping_list').hasClass('in')){
							$.cocoaDialog.close();
						}
					};
				}
			}
		});
	};
	var getReviewCountFromShoppingList = function(prod_code_list){
		$.ajax({
			type : 'POST',
			data : {'prod_code_list' : prod_code_list},
			url : ('/shop/get_review_count_from_shopping_list.cm'),
			dataType : 'json',
			cache : false,
			async : true,
			success : function(res){
				// $('"#' +  + '"').find("._review_count_text").text(res.review_count);
				// $(prod_code).find("._wish_count_text").text(res.wish_count);
			}
		});
	};

	/**
	 * review, qna 권한이 buyer일때 체크 모달
	 * @param prod_code
	 * @param type
	 */
	var openBuyerReview = function(prod_code){
		$.ajax({
			type : 'POST',
			data : {'prod_code' : prod_code},
			url : ('/shop/open_buyer_review.cm'),
			dataType : 'json',
			cache : false,
			async : false,
			success : function(result){
				if(result.msg === 'SUCCESS'){
					$.cocoaDialog.open({type : 'eduModal', custom_popup : result.html, width : 800, hide_event : function(){
						}});
				}else{
					alert(result.msg);
				}
			}
		});
	};

	var deleteReviewImage = function(obj){
		var box_obj = obj.parent().parent();
		obj.parent().remove();
		if(box_obj.find('.file-add').length == 0)box_obj.hide();
	};

	/**
	 * 상품상세 페이지에 뿌려지는 review, qna 의 카운트 가져오기.
	 * @param prod_code
	 */
	var getReviewQnaCount = function(prod_code){
		$.ajax({
			type : 'POST',
			data : {'prod_code' : prod_code},
			url : ('/shop/get_review_qna_count.cm'),
			dataType : 'json',
			cache : false,
			async : true,
			success : function(res){
				setReviewQnaCountText($("._review_count_text"), res.review_count);
				setReviewQnaCountText($("._qna_count_text"), res.qna_count);
			}
		});
	};

	var setReviewQnaCountText = function($target, count) {
		$target.text(count);
	};

	var getCurrentProdNo = function(){
		return current_prod_idx;
	};

	var saveSelectedProd = function(){
		if ( typeof window.sessionStorage === "undefined" ) {
			return false;
		}

		// 초기화
		var session_storage_keys = Object.keys(sessionStorage);
		session_storage_keys.forEach(function(_key) {
			if ( _key.indexOf('PROD_SELECTED_OPTION_') !== -1 ) {
				sessionStorage.removeItem(_key);
			}
		});

		// 마지막으로 본 것만 남도록 추가
		sessionStorage.setItem("LAST_SELECTED_PROD", current_prod_idx);
		sessionStorage.setItem(("PROD_SELECTED_OPTION_" + current_prod_idx), JSON.stringify({
			'prodIdx' : current_prod_idx,
			'optDataList' : selected_options,
			'orderCount' : order_count
		}));
	};

	var setSelectedProd = function(is_set){
		if ( typeof window.sessionStorage === "undefined" ) {
			return false;
		}

		var selected_data = JSON.parse(sessionStorage.getItem("PROD_SELECTED_OPTION_" + current_prod_idx));
		var last_prod_idx = sessionStorage.getItem("LAST_SELECTED_PROD");

		// 초기화
		var session_storage_keys = Object.keys(sessionStorage);
		session_storage_keys.forEach(function(_key) {
			if ( _key.indexOf('PROD_SELECTED_OPTION_') !== -1 ) {
				sessionStorage.removeItem(_key);
			}
		});

		// 마지막으로 본 상품이랑 일치하지 않는경우 리셋하고 끝
		if ( last_prod_idx != current_prod_idx ) 		return false;
		if ( typeof selected_data == "undefined")		return false;
		if ( selected_data == null )					return false;
		if ( Object.keys(selected_data).length <= 0 ) 	return false;
		if ( !is_set ) 									return false;

		for ( var _key  in selected_data ) {
			switch(_key){
				case "optDataList":
					selected_options = selected_data[_key];
					break;
				case "orderCount":
					order_count = selected_data[_key];
					break;
			}
		}
		updateSelectedOptions('prod');
		$prod_detail.addClass('open');
	};

	var openCouponDownload = function(){
		$.ajax({
			type : 'POST',
			data : {},
			url : ('/shop/open_coupon_download.cm'),
			dataType : 'json',
			cache : false,
			async : false,
			success : function(result){
				if(result.msg === 'SUCCESS'){
					$.cocoaDialog.open({type : 'site_alert', custom_popup : result.html, width : 800});
				}else{
					alert(result.msg);
				}
			}
		});
	};

	var setReviewCarousel = function(){
		var checkWidth = $(window).width();
		var owl = $('._review_modal_body .owl-carousel');
		if ( checkWidth > 768 ) {
			owl.owlCarousel({
				'loop': owl.children().length > 1,
				'margin':0,
				'nav':false,
				'items': 1
			});
		}
	};

	var setReviewClass = function(idx){
		if ( idx <= 0 ) {
			$body.toggleClass('no-images', true);
			$body.find('.modal_prod_detail.review .modal-content').toggleClass('clearfix', true);
		} else {
			$body.toggleClass('no-images', false);
			$body.find('.modal_prod_detail.review .modal-content').toggleClass('clearfix', false);
		}
	};

	var setReviewBtnAnimation = function(){
		var $btn_nav = $body.find('._btn_nav');
		setTimeout(function(){
			$btn_nav.attr('aria-hidden', true);
		}, 3000);
		$body.find('.modal-left').off('touchstart').on('touchstart', function(){
			$btn_nav.attr('aria-hidden', false);
			setTimeout(function(){
				$btn_nav.attr('aria-hidden', true);
			}, 3000);
		});
	};

	var setReviewSwipe = function(prev_idx, next_idx){
		$body.find('._review_modal_body').swipe( {
			swipe: function(event, direction, distance, duration, fingerCount, fingerData) {
				if(direction == 'left'){
					if (next_idx > 0){
						$('body').find('._btn_nav_wrap').remove();
						changeReviewDetail(next_idx);
					}
				}else if(direction == 'right') {
					if (prev_idx > 0) {
						$('body').find('._btn_nav_wrap').remove();
						changeReviewDetail(prev_idx);
					}
				}
			},
			threshold: 0,
			allowPageScroll: 'vertical',
			excludedElements: 'button, a, textarea, input'
		});
	};

	var showSeeMoreButton = function(){
		$prod_detail_content_pc.toggleClass('active', true);
		$prod_detail_content_mobile.toggleClass('active', true);
	};
	var hideSeeMoreButton = function(){
		$prod_detail_content_pc.toggleClass('active', false);
		$prod_detail_content_mobile.toggleClass('active', false);
	};

	var openRequireSelect = function(option_code){
		var $form_select = $('._form_select_wrap_'+option_code);
		var $goods_wrap = $body.find('#goods_wrap');
		$form_select.addClass('open');
		if($('body').hasClass('mobile_focus_on')){
			$('body').removeClass('mobile_focus_on');
		}
		var top = Math.floor($form_select.offset().top); // 셀렉트 박스의 위치
		var scroll_top =  Math.floor($goods_wrap.scrollTop()); // 컨테이너의 스크롤 top 위치
		var position_top = $('#option_'+option_code).position().top;
		var height = Math.floor($('.opt-group').height()); // 스크롤 영역의 높이
		var default_value = 180;

		$form_select.parent('._form_parent').nextAll().toggleClass('disabled', true);
		if ( top - height >= default_value ) {
			$goods_wrap.animate({ scrollTop: position_top - 50 }, 0);
			if ( scroll_top >= default_value ) {
				$goods_wrap.animate({ scrollTop: position_top + $('.option_box_wrap').outerHeight() }, 0);
			}
		}
	};

	return {
		addProdWish : function(prod_code){
			addProdWish(prod_code);
		},
		/** type (prod/cart) */
		increaseOptionCount : function(optNo, type){
			increaseOptionCount(optNo, function(){
				updateSelectedOptions(type);
			});
		},
		/** type (prod/cart) */
		decreaseOptionCount : function(optNo, type){
			decreaseOptionCount(optNo, function(){
				updateSelectedOptions(type);
			});
		},
		// initDetail : function(prodIdx, price, requireOptionCnt, use_np_mobile, use_lazyload, tab_type,is_site_page, is_digital, is_prod_detail_page, view_price, cm_data, section_code){
		// 	initDetail(prodIdx, price, requireOptionCnt, use_np_mobile, use_lazyload, tab_type,is_site_page, is_digital, is_prod_detail_page, view_price, cm_data, section_code);
		// },
		initDetail : function(option){
			initDetail(option);
		},
		initProdStock : function(stock_use, stock, stock_un_limit) {
			initProdStock(stock_use, stock, stock_un_limit);
		},
		initLocalize : function(code){
			initLocalize(code);
		},
		selectOption : function(prod_idx, optList, require, count, success, failed){
			selectOption(prod_idx, optList, require, count, function(){
				success();
			}, function(msg){
				failed(msg);
			});
		},
		removeSelectedOption : function(optNo, type){
			removeSelectedOption(optNo, function(){
				selected_require_options = [];		// 선택된 필수 옵션 초기화
				loadOption(type, current_prod_idx);		// 옵션을 다시 로드함
				updateSelectedOptions(type);
			});
		},
		selectRequireOption : function(type, prod_idx, option_code, value_code, value_name, success){
			selectRequireOption(type, prod_idx, option_code, value_code, value_name, function(){
				success();
			});
		},
		selectOptionalOption : function(prod_idx, option_code, value_code, value_name, success){
			selectOption(prod_idx, [{
				'value_type': 'SELECT',
				'option_code' : option_code,
				'value_code' : value_code,
				'value_name' : value_name
			}], false, 1, function(){
				success();
			}, function(msg){
				alert(msg);
			});
		},
		/** type (prod/cart) */
		changeOptionCount : function(optNo, optCount, type){
			changeOptionCount(optNo, optCount, function(){
				updateSelectedOptions(type);
			});
		},
		"getCurrentProdNo": function(){
			return getCurrentProdNo();
		},
		"saveSelectedProd": function(){
			saveSelectedProd();
		},
		"setSelectedProd": function(is_set){
			setSelectedProd(is_set);
		},
		addOrder : function(type, backurl, params){
			if(add_order_progress_check) return false;
			if ( typeof AM_PRODUCT != 'undefined' ) AM_PRODUCT(order_count);
			addOrder(type, backurl, function(result){
				switch( type ) {
					case 'npay':
						if(result.npay_url == '') {
							if ( result.errmsg ) {
								alert(escape_javascript(result.errmsg));
							} else {
								alert(LOCALIZE.설명_네이버페이상품구매실패(escape_javascript(result.errmsg)));
							}
							add_order_progress_check = false;
						} else {
							if ( !! result.shopping_additional_price_msg ) {
								if ( ! confirm(result.shopping_additional_price_msg + '\n\n' + LOCALIZE.설명_네이버페이를계속해서진행하시겠습니까()) ) {
									add_order_progress_check = false;
									return false;
								}
							}

							if ( result.auth_type == 2 ) {
								// 성인 인증이 필요할 경우
								window.location.href = '/?mode=adult_auth_npay&data=' + result.data;
							} else {
								// 성인 인증이 필요하지 않는 경우
								var npay_order_info = result['npay_order_info'];

								if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder();
								if ( typeof FB_PIXEL != 'undefined'){
									FB_PIXEL.InitiateCheckout(result.ic_event_id,result.total_price,result.currency,result.fb_external_id);
									if(result.fb_npay_switch === 'Y') FB_PIXEL.addNpayOrder(npay_order_info);
								}

								if ( result.google_analytics_type == 'G' && result.is_ga_api_secret === false ) {
									if ( typeof GOOGLE_ANAUYTICS != 'undefined') GOOGLE_ANAUYTICS.addNpayOrder(npay_order_info);
								}
								if ( typeof GOOGLE_ADWORDS_TRACE != 'undefined') GOOGLE_ADWORDS_TRACE.addNpayOrder(npay_order_info);
								if ( typeof CRITEO != 'undefined') CRITEO.npayTrackTransaction(npay_order_info);
								window.location.href = result.npay_url;
							}
						}
						break;
					case 'talkpay':
						switch ( params.type ) {
							case 'onOrder':
								if ( result.msg == 'SUCCESS' ) {
									if ( result.order_sheet_id ) {
										params.onSuccess(result.order_sheet_id);
									} else {
										params.onFailure({message: result.msg});
									}
								} else {
									params.onFailure({message: result.msg});
								}
								break;
							case 'onPayOrder':
								if ( result.msg == 'SUCCESS' ) {
									if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.InitiateCheckout(result.ic_event_id,result.total_price,result.currency,result.fb_external_id);
									if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder(result.order_code);
									window.location.href = "/shop_payment/?order_code=" + encodeURIComponent(result.order_code);
								}
								break;
						};

						add_order_progress_check = false;
						break;
					default:
						if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.InitiateCheckout(result.ic_event_id,result.total_price,result.currency,result.fb_external_id);
						if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder(result.order_code);
						window.location.href = "/shop_payment/?order_code=" + encodeURIComponent(result.order_code);
						break;
				}
			});
		},
		addCart : function(){
			addCart(function(){
				$add_cart_alarm.show();
			});
		},
		"digitalFileDownload": function(order_no){
			digitalFileDownload(order_no);
		},
		updateSelectedOptions : function(type){
			updateSelectedOptions(type);
		},
		getSelectedOption : function(){
			return selected_options;
		},
		changeOrderCount : function(type, count, success, is_alert){
			return changeOrderCount(type, count, success, is_alert);
		},
		checkProdStock : function(cnt) {
			return checkProdStock(cnt);
		},
		increaseOrderCount : function(type, success){
			return increaseOrderCount(type, success);
		},
		decreaseOrderCount : function(type, success){
			return decreaseOrderCount(type, success);
		},
		showMobileOptions : function(){
			return showMobileOptions();
		},
		showMobileFirstSelect : function(){
			return showMobileFirstSelect();
		},
		hideMobileOptions : function(){
			return hideMobileOptions();
		},
		showPCOptions : function(type, backurl){
			return showPCOptions(type, backurl);
		},
		hidePCOptions : function(){
			return hidePCOptions();
		},
		scrollPCTab : function(type){
			return scrollPCTab(type);
		},
		changeContentTab : function(type, r_p, q_p, paging_on,only_photo){
			return changeContentTab(type, r_p, q_p, paging_on,only_photo);
		},
		changeContentPCTab : function(type, r_p, q_p, paging_on,only_photo,shop_view_body_width){
			return changeContentPCTab(type, r_p, q_p, paging_on,only_photo,shop_view_body_width);
		},
		removeReviewHash : function(){
			return removeReviewHash();
		},
		removeQnawHash : function(){
			return removeQnawHash();
		},
		getOnlyPhotoReview : function(only_photo_switch,is_mobile,is_one_page){
			return getOnlyPhotoReview(only_photo_switch,is_mobile,is_one_page);
		},
		getFirstPhotoReview: function(page){
			return getFirstPhotoReview(page);
		},
		viewPhotoReviewMore: function(is_mobile, is_one_page){
			return viewPhotoReviewMore(is_mobile, is_one_page);
		},
		getReviewSummary: function(){
			return getReviewSummary();
		},
		changeReviewDetail : function(idx){
			return changeReviewDetail(idx);
		},
		viewReviewDetail : function(idx,r_p,only_photo,is_hash){
			return viewReviewDetail(idx,r_p,only_photo,is_hash);
		},
		"setMembershipSaleData": function(data){
			setMembershipSaleData(data);
		},
		"setPeriodDiscountData" : function(flag,$data2,group_list){
			setPeriodDiscountData(flag,$data2,group_list);
		},
		viewQnaDetail : function(idx,q_p,is_hash){
			return viewQnaDetail(idx,q_p,is_hash);
		},
		changeProdImageRolling : function(no){
			return changeProdImageRolling(no);
		},
		startProdImageRolling : function(autoWidth){
			return startProdImageRolling(autoWidth);
		},
		setOrderCount : function(count){
			order_count = count;
		},
		getOrderCount : function(){
			return order_count;
		},
		addNPayWish : function(){
			return addNPayWish();
		},
		hideAddCartAlarm : function(){
			hideAddCartAlarm();
		},
		loadOption : function(type, prod_idx){
			return loadOption(type, prod_idx);
		},
		loadDelivSetting : function(prod_idx,change_country,deliv_type,deliv_pay_type){
			return loadDelivSetting(prod_idx,change_country,deliv_type,deliv_pay_type);
		},
		changeRequireInputOption : function(type, prod_idx, option_code, msg, success){
			changeRequireInputOption(type, prod_idx, option_code, msg, function(){
				success();
			});
		},
		changeInput : function(){
			changeInput();
		},
		changeTab : function(type){
			changeTab(type);
		},
		countryCodeChange : function(country){
			countryCodeChange(country);
		},
		DetailItemMake : function(idx,change_country,deliv_type,deliv_pay_type){
			return DetailItemMake(idx,change_country,deliv_type,deliv_pay_type);
		},
		addDelivType : function(type){
			$deliv_type = type
		},
		addDelivPayType : function(type){
			$deliv_pay_type = type;
		},
		visitFormMake : function(){
			$deliv_visit_wrap.show()
		},
		openProdDetailFromShoppingList : function(idx, back_url, is_prod_detail_page, is_mobile, prod_idx_org){
			return openProdDetailFromShoppingList(idx, back_url, is_prod_detail_page, is_mobile, prod_idx_org);
		},
		'getReviewQnaCount' : function(prod_code){
			return getReviewQnaCount(prod_code);
		},
		'setReviewQnaCountText' : function($target, count){
			return setReviewQnaCountText($target, count);
		},
		getReviewCountFromShoppingList : function(prod_code_list){
			return getReviewCountFromShoppingList(prod_code_list);
		},
		openBuyerReview : function(prod_code){
			return openBuyerReview(prod_code);
		},
		deleteReviewImage : function(obj){
			return deleteReviewImage(obj);
		},
		openCouponDownload : function(){
			return openCouponDownload();
		},
		setReviewCarousel: function(){
			return setReviewCarousel();
		},
		setReviewClass: function(idx){
			return setReviewClass(idx);
		},
		setReviewBtnAnimation: function(){
			return setReviewBtnAnimation();
		},
		setReviewSwipe: function(prev_idx, next_idx){
			return setReviewSwipe(prev_idx, next_idx);
		},
		showSeeMoreButton: function(){
			return showSeeMoreButton();
		},
		hideSeeMoreButton: function(){
			return hideSeeMoreButton();
		},
		openRequireSelect : function(option_code){
			return openRequireSelect(option_code);
		},
	}
}();

var SITE_SHOP_CART = function(){
	var selectedCartItem = [];
	var $cartItemCheckboxList;
	var $cartAllCheckBox;
	var $shop_cart_list;
	var $shop_cart_wish_list;
	var $shop_cart_wish_list_empty;
	var $changeCartItemLayer;
	var $total_price;

	var $cart_list_wrap;
	var $cart_pay_number;

	var is_cart_changed = false;
	var currentCartCode = '';
	var current_backurl = '';
	var currentChangeCartItemCode = '';

	var add_order_progress_check = false;

	var initCart = function(cart_code, backurl){
		currentCartCode = cart_code;
		current_backurl = backurl;
		$shop_cart_list = $('#shop_cart_list');
		$changeCartItemLayer = $('#shop_cart_change_layer');
		$shop_cart_wish_list = $('#shop_cart_wish_list');
		$shop_cart_wish_list_empty = $('#shop_cart_wish_list_empty');

		$cart_list_wrap = $shop_cart_list.find('._cart_list_wrap');
		cartListMake(backurl);
	};

	/**
	 * 선택한 항목의 가격 계산
	 */
	var getSelectedPrice = function(){
		$.ajax({
			type : 'POST',
			data : {'item_list' : selectedCartItem},
			url : ('/shop/get_cart_price.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					setCartPriceHtml(res['price_data']);
				}else {
					alert(res.msg);
					is_cart_changed = false;
				}
			}
		});
	};

	var setCartPriceHtml = function(data) {
		if (  typeof $total_price == 'undefined' || $total_price.length == 0 )			$total_price = $('#cart_main_total_price');
		if ( typeof $cart_pay_number == 'undefined' || $cart_pay_number.length == 0 ) 	$cart_pay_number = $cart_list_wrap.find('.pay-number');

		$cart_pay_number.find('._cart_price').html(data['price']);
		$cart_pay_number.find('._cart_deliv_price').html(data['deliv_price']);
		$cart_pay_number.find('._cart_membership_discount').html(data['sale_price']);
		$cart_pay_number.find('._cart_add_point').html(data['add_point']);
		$cart_list_wrap.find('._cart_membership_field').toggle(data['membership_price_exist']);
		$cart_list_wrap.find('._cart_period_field').toggle(data['period_price_exist']);
		$cart_list_wrap.find('._cart_period_discount').html(data['period_price']);

		$total_price.html(data['total_price']);
	};

	/**
	 * 상품 위시리스트 추가 처리
	 * @param cart_item_list
	 */
	var addProdWish = function(cart_item_list){
		$.ajax({
			type : 'POST',
			data : {'type' : 'add', 'cart_item_list' : cart_item_list},
			url : ('/shop/add_prod_wish_cart.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					for(var i = 0; i < cart_item_list.length; i++){
						if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.AddToWishlist();
						$shop_cart_list.find("._wish_status_" + cart_item_list[i]).addClass('check');
					}
					loadProdWishList();
				}else
					alert(res.msg);
			}
		});
	};

	/**
	 * 위시리스트 가져오기
	 * @param prod_code
	 */
	var loadProdWishList = function(){
		$.ajax({
			type : 'POST',
			data : {'type' : 'cart'},
			url : ('/shop/get_prod_wish_list.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					if(res.count > 0){
						$shop_cart_wish_list_empty.hide();
						$shop_cart_wish_list.show().html(res.html);
						setTimesale();
					}else{
						$shop_cart_wish_list_empty.show();
						$shop_cart_wish_list.hide();
					}
				}else
					alert(res.msg);
			}
		});
	};

	/**
	 * 위시리스트 제거
	 * @param cart_item_list
	 */
	var deleteProdWish = function(cart_item_list){
		$.ajax({
			type : 'POST',
			data : {'type' : 'delete', 'cart_item_list' : cart_item_list},
			url : ('/shop/add_prod_wish_cart.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					for(var i = 0; i < cart_item_list.length; i++){
						$shop_cart_list.find("._wish_status_" + cart_item_list[i]).removeClass('check');
					}
					loadProdWishList();
				}else
					alert(res.msg);
			}
		});
	};

	/**
	 * 위시리스트 제거 (상품코드사용
	 * @param prod_code
	 */
	var deleteProdWishByProdCode = function(prod_code){
		$.ajax({
			type : 'POST',
			data : {'type' : 'delete_prodcode', 'prod_code' : prod_code},
			url : ('/shop/add_prod_wish_cart.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					window.location.reload();
				}else
					alert(res.msg);
			}
		});
	};

	/**
	 * 특정 카트 아이템 삭제
	 */
	var removeCartItem = function(item_codeList){
		$.ajax({
			type : 'POST',
			data : {'item_codeList' : item_codeList},
			url : ('/shop/remove_cart_item.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					if(typeof AW_DEL != 'undefined'){
						for(var i = 0; i < item_codeList.length; i++){
							var itme_info = result.delete_cart_info[item_codeList[i]];
							AW_DEL(itme_info['prod_no'], itme_info['count']);
						}
					}
					if(typeof AM_DEL != 'undefined'){
						for(var i = 0; i < item_codeList.length; i++){
							var itme_info = result.delete_cart_info[item_codeList[i]];
							AM_DEL(itme_info['prod_no'], itme_info['count']);
						}
					}
					if ( typeof CHANNEL_PLUGIN != "undefined" ) CHANNEL_PLUGIN.updateChannelProfileAttr('cart');
					window.location.href = "/shop_cart";
				}else{
					alert(result.msg);
				}
			}
		});
	};

	/**
	 * 특정 카트 아이템 삭제
	 */
	var removeCartItemOption = function(item_code, optionNo){
		$.ajax({
			type : 'POST',
			data : {'item_code' : item_code, 'optionNo' : optionNo},
			url : ('/shop/remove_cart_item_option.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					window.location.reload();
				}else{
					alert(result.msg);
				}
			}
		});
	};

	/**
	 * 체크박스 전체 선택 제어
	 * @param bool b
	 */
	var toggleAllCheckCartItem = function(b){
		$cart_list_wrap.find('._cartItemCheckbox').each(function(){
			$(this).prop('checked', (b === true));
		});
		CartItemChanged();
	}

	/**
	 * 체크박스 선택 제어
	 */
	var toggleCheckCartItem = function(){
		var $cartItemCheckbox = $cart_list_wrap.find('._cartItemCheckbox');
		var $cartItemCheckboxSeleted = $cart_list_wrap.find('._cartItemCheckbox:checked');
		$cart_list_wrap.find('._all_check').prop('checked', ($cartItemCheckbox.length == $cartItemCheckboxSeleted.length));
		CartItemChanged();
	};

	/**
	 * 체크박스 변경시 재계산
	 */
	var CartItemChanged = function(){
		// 초기화
		selectedCartItem = [];
		$cart_list_wrap.find('._cartItemCheckbox:checked').each(function(){
			selectedCartItem.push($(this).val());
		});
		getSelectedPrice();
	};

	/**
	 * 전체 선택인지 아닌지 체크
	 */
	var toggleAllSelectCk = function(){
		var $cartItemSelectCount = 0;
		$cartItemCheckboxList = $shop_cart_list.find("input._cartItemCheckbox");
		$cartAllCheckBox = $shop_cart_list.find("input._all_check");
		$cartItemCheckboxList.each(function(){
			if($(this).is(":checked") === true){
				$cartItemSelectCount++;
			}
		});
		if($cartItemCheckboxList.length == $cartItemSelectCount){
			$cartAllCheckBox.prop('checked',true);
		}else{
			$cartAllCheckBox.prop('checked',false);
		}
	}

	/**
	 * 카트 전체 주문하기
	 * @param direct true/false (선택유무와 상관없이 바로 주문)
	 */
	var addOrderWithCart = function(type, item_code, backurl, direct, params){
		if ( is_cart_changed ) return false;
		if ( add_order_progress_check ) return false;
		if ( currentCartCode == '' ) {
			alert(LOCALIZE.설명_장바구니가비어있습니다());
			return false;
		}

		var item_code_list = [];
		if ( item_code == '' )
			item_code_list = selectedCartItem;
		else
			item_code_list = [item_code];

		if ( item_code_list.length <= 0 ) {
			alert(LOCALIZE.설명_주문하실상품을선택해주세요());
			return false;
		}

		add_order_progress_check = true;

		var _data = {
			'type' : type,
			'cart_code' : currentCartCode,
			'item_code_list' : item_code_list,
			'backurl' : backurl,
			'direct' : (direct ? 'Y' : 'N')
		};

		_data.ace_pid = window._AcePID || window._AceMID;

		$.ajax({
			type : 'POST',
			data : _data,
			url : ('/shop/add_order_cart.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if ( result.msg == 'SUCCESS' ) {
					switch( type ) {
						case 'npay':
							if ( result.npay_url == '' ) {
								if ( result.errmsg ) {
									alert(escape_javascript(result.errmsg));
								} else {
									alert(LOCALIZE.설명_네이버페이상품구매실패(escape_javascript(result.errmsg)));
								}
							} else {
								if ( !!result.shopping_additional_price_msg ) {
									alert(result.shopping_additional_price_msg);
								}

								if ( result.auth_type == 2 ) {
									// 성인 인증이 필요할 경우
									window.location.href = '/?mode=adult_auth_npay&data=' + result.data;
								} else {
									// 성인 인증이 필요하지 않는 경우
									// 네이버 페이 구매시 전환추적 추가
									var npay_order_info = result['npay_order_info'];
									if ( typeof FB_PIXEL != 'undefined' ) {
										FB_PIXEL.InitiateCheckout(result.ic_event_id, result.total_price, result.currency, result.fb_external_id);
										if ( result.fb_npay_switch === 'Y' ) FB_PIXEL.addNpayOrder(npay_order_info);
									}
									if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder();
									if ( result.google_analytics_type == 'G' && result.is_ga_api_secret === false ) {
										if ( typeof GOOGLE_ANAUYTICS != 'undefined' ) GOOGLE_ANAUYTICS.addNpayOrder(npay_order_info);
									}
									if ( typeof GOOGLE_ADWORDS_TRACE != 'undefined' ) GOOGLE_ADWORDS_TRACE.addNpayOrder(npay_order_info);
									if ( typeof CRITEO != 'undefined' ) CRITEO.npayTrackTransaction(npay_order_info);

									window.location.href = result.npay_url;
								}
							}
							break;
						case 'talkpay':
							switch ( params.type ) {
								case 'onOrder':
									if ( result.msg == 'SUCCESS' ) {
										if ( result.order_sheet_id ) {
											params.onSuccess(result.order_sheet_id);
										} else {
											params.onFailure({message: result.msg});
										}
									} else {
										params.onFailure({message: result.msg});
									}
									break;
								case 'onPayOrder':
									if ( result.msg == 'SUCCESS' ) {
										if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.InitiateCheckout(result.ic_event_id, result.total_price, result.currency, result.fb_external_id);
										if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder();
										window.location.href = "/shop_payment/?order_code=" + encodeURIComponent(result.order_code);
									}
									break;
							};

							add_order_progress_check = false;
							break;
						default:
							if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.InitiateCheckout(result.ic_event_id, result.total_price, result.currency, result.fb_external_id);
							if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder();
							window.location.href = "/shop_payment/?order_code=" + encodeURIComponent(result.order_code);
					}
				} else {
					if(type == 'talkpay'){
						switch ( params.type ) {
							case 'onOrder':
								params.onFailure({message: result.msg});
								break;
							case 'onPayOrder':
								if ( result.msg == 'SUCCESS' ) {
									if ( typeof FB_PIXEL != 'undefined' ) FB_PIXEL.InitiateCheckout(result.ic_event_id, result.total_price, result.currency, result.fb_external_id);
									if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.AddOrder();
									window.location.href = "/shop_payment/?order_code=" + encodeURIComponent(result.order_code);
								}
								break;
						};
					}else{
						alert(result.msg);
					}
					/*
					새로고침 할 필요가 없음..! (문제 시 롤백해야함)
					location.reload();
					 */
					add_order_progress_check = false;
				}
			}
		});
	};
	/**
	 * 카트 전체 네이버찜등록
	 */
	var addNPayWishWithCart = function(item_code){
		if(currentCartCode == ''){
			alert(LOCALIZE.설명_장바구니가비어있습니다());
			return false;
		}
		$.ajax({
			type : 'POST',
			data : {'cart_code' : currentCartCode, 'item_code' : item_code},
			url : ('/shop/add_npay_wish_cart.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					if(result.npay_url == '')
						alert(LOCALIZE.설명_네이버페이찜등록실패(escape_javascript(result.errmsg)));
					else{
						if(result.mobile == 'Y')
							window.location.href = result.npay_url;
						else
							window.open(result.npay_url);
					}
				}else{
					alert(result.msg);
				}
			}
		});
	};
	/**
	 * 장바구니 옵션변경 화면 표시
	 */
	var showChangeCartItem = function(item_code){
		$.ajax({
			type : 'POST',
			data : {'item_code' : item_code},
			url : ('/shop/change_cart_item_request.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					$changeCartItemLayer.find('div.container-fluid').html(result.html);
					$changeCartItemLayer.show();

					SITE_SHOP_DETAIL.initDetail({
						prod_idx: result.prodIdx,
						prod_price: result.price,
						require_option_count: result.require_option_count,
						shop_use_full_load: false,
					});

					SITE_SHOP_DETAIL.initProdStock(result.prod_stock.stock_use, result.prod_stock.stock_no_option, result.prod_stock.stock_unlimit);
					SITE_SHOP_CART.changeCartLoadOption(result.prodIdx);
					var selected_option_count = result.selected_option_list.length;
					var cnt = 0;
					currentChangeCartItemCode = item_code;
					$.each(result.selected_option_list, function(no, data){
						SITE_SHOP_DETAIL.selectOption(result.prodIdx, data.options, data.require, data.count, function(){
							cnt++;
							if(cnt >= selected_option_count) SITE_SHOP_DETAIL.updateSelectedOptions('cart');
						}, function(){
							cnt++;
							if(cnt >= selected_option_count) SITE_SHOP_DETAIL.updateSelectedOptions('cart');
						});
					});
					if(result.require_option_count == 0){
						SITE_SHOP_CART.changeCartOrderCount("pc", result.count, false);
						SITE_SHOP_CART.changeCartOrderCount("mobile", result.count, false);
					}
				}else{
					alert(result.msg);
				}
			}
		});
	};
	var hideChangeCartItem = function(){
		$changeCartItemLayer.find('div.container-fluid').empty();
		$changeCartItemLayer.hide();
		currentChangeCartItemCode = '';
	};
	/**
	 * 장바구니 옵션 변경 완료 처리
	 */
	var changeCartItemComplete = function(){
		if(currentChangeCartItemCode != ''){
			changeCartItemData({
				"mode": "detail",
				"item_code": currentChangeCartItemCode,
				"options": SITE_SHOP_DETAIL.getSelectedOption(),
				"order_count": SITE_SHOP_DETAIL.getOrderCount()
			});
		}
	};

	var changeCartItemDelivType = function(item_code, deliv_type){
		changeCartItemData({
			"mode": "delivery",
			"item_code": item_code,
			"deliv_type": deliv_type
		});
	};

	var changeCartItemDelivPayType = function(item_code, deliv_pay_type) {
		changeCartItemData({
			"mode": "delivery",
			"item_code": item_code,
			"deliv_pay_type": deliv_pay_type
		});
	};

	var changeCartItemData = function(dt){
		if( typeof dt['item_code'] == 'undefined' )	return false;
		$.ajax({
			type : 'POST',
			data : dt,
			url : ('/shop/change_cart_item.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg == 'SUCCESS'){
					if(typeof AW_F_D != 'undefined'){
						if(result.before_count > result.after_count){
							AW_F_D(result.prod_no,'o',result.before_count - result.after_count);
						}else{
							AW_F_D(result.prod_no,'i',result.after_count - result.before_count);
						}
					}
					if( typeof AM_INOUT !== 'undefined' ) {
						AM_INOUT(result.prod_no,result.after_count);
					}
					if ( typeof CHANNEL_PLUGIN != "undefined" ) CHANNEL_PLUGIN.updateChannelProfileAttr('cart');
					window.location.reload();
				}else{
					alert(result.msg);
				}
			}
		});
	};

	var countryCodeChange = function(country){
		$.ajax({
			type : 'POST',
			data : {'country':country},
			url : ('/shop/country_code_change.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(result.msg != "SUCCESS"){
					alert(result.msg);
				}else{
					window.location.reload();
				}
			}
		});
	}

	var cartListMake = function(current_full_url){
		selectedCartItem = [];
		$.ajax({
			type : 'POST',
			data : {'current_full_url':current_full_url},
			url : ('/shop/get_cart_list_make.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					$shop_cart_list.find('._cart_list_wrap').html(res.html);
				}else
					alert(res.msg);

				is_cart_changed = false;
			}
		});
	};

	var setTimesale = function(){
		var $doz_timesale_wrap = $shop_cart_wish_list.find('._doz_timesale_wrap');
		if($doz_timesale_wrap.length > 0){
			$doz_timesale_wrap.each(function(){
				var $that = $(this);
				var start_time = ($that.find('._doz_timesale').attr('data-start-time') * 1000);
				var $doz_timesale = $that.find('._doz_timesale');
				var timesale_interval = setInterval(function(){
					var remain_ms = ($doz_timesale.attr('data-end-time') * 1000) - start_time;
					if(remain_ms > 0){
						var remain_d = Math.floor(remain_ms / 86400000);
						var remain_h = Math.floor((remain_ms % 86400000) / 3600000);
						var remain_m = Math.floor((remain_ms % 3600000) / 60000);
						var remain_s = Math.floor((remain_ms % 60000) / 1000);

						var remain_hh = remain_h < 10 ? '0' + remain_h : '' + remain_h;
						var remain_mm = remain_m < 10 ? '0' + remain_m : '' + remain_m;
						var remain_ss = remain_s < 10 ? '0' + remain_s : '' + remain_s;
						if(remain_d >= 1){
							$doz_timesale.text(getLocalizeString('설명_타임세일종료까지n일', [remain_d], '타임세일 종료까지 %1일'));
						}else{
							$doz_timesale.text(getLocalizeString('설명_종류까지n1시n2분n3초남음', [remain_hh, remain_mm,remain_ss], '종료까지 %1:%2:%3 남음'));
						}
						start_time = start_time + 1000;
					}else{
						/* 타임세일 종료 */
						clearInterval(timesale_interval);
						$that.remove();
					}
				}, 1000);
			});
		}
	};

	return {
		initCart : function(cart_code, backurl){
			initCart(cart_code, backurl);
		},
		loadProdWishList : function(){
			loadProdWishList();
		},
		deleteProdWish : function(cart_item){
			deleteProdWish([cart_item]);
		},
		deleteProdWishByProdCode : function(prod_code){
			deleteProdWishByProdCode(prod_code);
		},
		addProdWish : function(cart_item){
			addProdWish([cart_item]);
		},
		addProdWishMulti : function(){
			if(selectedCartItem.length == 0){
				alert(LOCALIZE.설명_선택한항목이없습니다());
				return false;
			}
			addProdWish(selectedCartItem);
		},
		removeCartItemOption : function(item_code, optionNo){
			removeCartItemOption(item_code, optionNo);
		},
		removeCartItem : function(item_code){
			removeCartItem([item_code]);
		},
		removeSelectedCartItem : function(){
			if(selectedCartItem.length == 0){
				alert(LOCALIZE.설명_선택한항목이없습니다());
				return false;
			}
			removeCartItem(selectedCartItem);
		},
		"toggleCheckCartItem": function(){
			toggleCheckCartItem();
		},
		"toggleAllCheckCartItem": function(b){
			toggleAllCheckCartItem(b);
		},
		addOrderWithCart : function(type, item_code, backurl, params){
			addOrderWithCart(type, item_code, backurl, false, params);
		},
		addOrderWithCartDirect : function(type, item_code, backurl){
			addOrderWithCart(type, item_code, backurl, true);
		},
		showChangeCartItem : function(item_code){
			showChangeCartItem(item_code);
		},
		hideChangeCartItem : function(){
			hideChangeCartItem();
		},
		changeCartSelectRequireOption : function(prod_idx, option_code, value_code, value_name){
			SITE_SHOP_DETAIL.selectRequireOption('cart', prod_idx, option_code, value_code, value_name, function(){
				SITE_SHOP_DETAIL.updateSelectedOptions('cart');
			});
		},
		changeCartChangeRequireInputOption : function(prod_idx, option_code, msg){
			SITE_SHOP_DETAIL.changeRequireInputOption(prod_idx, option_code, msg, function(){
				SITE_SHOP_DETAIL.updateSelectedOptions('cart');
			});
		},
		changeCartSelectOptionalOption : function(prod_idx, option_code, value_code, value_name){
			SITE_SHOP_DETAIL.selectOptionalOption(prod_idx, option_code, value_code, value_name, function(){
				SITE_SHOP_DETAIL.updateSelectedOptions('cart');
			});
		},
		changeCartItemIncrease : function(optNo, reload){
			SITE_SHOP_DETAIL.increaseOptionCount(optNo, 'cart');
		},
		changeCartItemDecrease : function(optNo){
			SITE_SHOP_DETAIL.decreaseOptionCount(optNo, 'cart');
		},
		changeCartIncrease : function(type){
			var o = $changeCartItemLayer.find('input._order_count_' + type);
			var curCount = o.val();
			if(isNaN(curCount))
				curCount = 1;
			else
				curCount = parseInt(curCount) + 1;

			var res = SITE_SHOP_DETAIL.checkProdStock(curCount);
			if ( res === true ) {
				o.val(curCount);
				SITE_SHOP_DETAIL.setOrderCount(curCount);
			} else {
				alert(res);
			}

			SITE_SHOP_DETAIL.updateSelectedOptions('cart');
		},
		changeCartDecrease : function(type){
			var o = $changeCartItemLayer.find('input._order_count_' + type);
			var curCount = o.val();
			if(isNaN(curCount))
				curCount = 1;
			else
				curCount = parseInt(curCount) - 1;
			if(curCount < 1) curCount = 1;

			var res = SITE_SHOP_DETAIL.checkProdStock(curCount);
			if ( res === true ) {
				o.val(curCount);
				SITE_SHOP_DETAIL.setOrderCount(curCount);
			} else {
				alert(res);
			}

			SITE_SHOP_DETAIL.updateSelectedOptions('cart');
		},
		changeCartOrderCount : function(type, count, is_alert){
			if ( is_alert == void 0 ) is_alert = true;

			if(isNaN(count))
				count = 1;
			else
				count = parseInt(count);
			if(count < 1) count = 1;

			var res = SITE_SHOP_DETAIL.checkProdStock(count);
			if ( res === true ) {
				$changeCartItemLayer.find("input._order_count_" + type).val(count);
				SITE_SHOP_DETAIL.setOrderCount(count);
			} else {
				if ( is_alert ) alert(res);
			}

			SITE_SHOP_DETAIL.updateSelectedOptions('cart');
		},
		changeCartItemRemove : function(optNo){
			SITE_SHOP_DETAIL.removeSelectedOption(optNo, 'cart');
		},
		changeCartItemCount : function(optNo, optCount){
			SITE_SHOP_DETAIL.changeOptionCount(optNo, optCount, 'cart');
		},
		"changeCartItemDelivType": function(item_code, deliv_type){
			changeCartItemDelivType(item_code, deliv_type);
		},
		"changeCartItemDelivPayType": function(item_code, deliv_pay_type) {
			changeCartItemDelivPayType(item_code, deliv_pay_type);
		},
		changeCartItemComplete : function(){
			changeCartItemComplete();
		},
		addNPayWishWithCart : function(item_code){
			addNPayWishWithCart(item_code);
		},
		addSelectedCartItem : function(item_code){
			selectedCartItem.push(item_code);
		},
		changeCartLoadOption : function(prod_idx){
			SITE_SHOP_DETAIL.loadOption('cart', prod_idx);
		},
		countryCodeChange : function(country){
			countryCodeChange(country);
		},
		toggleAllSelectCk : function(){
			toggleAllSelectCk();
		}
	}
}();

var SITE_SHOP_REVIEW = function(){
	var $review_wrap;
	var $mobile_review_wrap;
	var $mobile_form;
	var $rating;
	var $star;
	var $m_rating;
	var $m_star;
	var $comment_body;
	var $comment_area;
	var review_body;
	var $review_container;
	var $review_form;
	var body_input;
	var placeholderText;
	var isIOS, isSafari, $fr_m_custom, $write_header, m_sticky_container_trigger_top, $toolbarContainer;
	var images = {};
	var add_review_wrap;
	var is_submit = false;

	var init = function(code){
		$review_wrap = $('._review_wrap');
		$review_form = $('#review_form');
		$rating = $('#rating');
		$star = $('._star');
		$review_form.find('._btn_add_image').toggleClass('no-margin', true);
		review_body = $('#review_modal_body');
		$review_container = $('#review_container');
		body_input = $('#body_input');
		placeholderText = $('#placeholderText').val();
		$review_form.find('#review_image_upload_btn').fileupload({
			url : '/ajax/review_image_upload.cm',
			formData : {temp : 'Y', target : 'site_review', type : 'image'},
			dataType : 'json',
			singleFileUploads : true,
			limitMultiFileUploads : 1,
			start : function(e, data){
			},
			progress : function(e, data){
			},
			done : function(e, data){
				$.each(data.result.files, function(e, tmp){
					if(tmp.error == null){
						$review_form.find("#review_image_box").show();
						$review_form.find('._btn_add_image').toggleClass('no-margin', false);
						var url = CDN_UPLOAD_URL + tmp.url;
						images[tmp.code] = tmp.url;
						var html = '<span class="file-add _img_'+tmp.code+'"><input type="hidden" name="img" value="' + tmp.name + '"><img src="' + url + '"><a class="del" href="javascript:;" onclick="SITE_SHOP_REVIEW.deleteReviewImage(\''+tmp.code+ '\')" ><i class="btm bt-times vertical-middle" aria-hidden="true"></i></a></span>';
						$review_form.find("#review_image_box ._btn_add_image").before(html);

					}else{
						alert(tmp.error);
					}
				});
			},
			fail : function(e, data){
				alert(getLocalizeString("설명_업로드에실패하였습니다", "", "업로드에 실패 하였습니다."));
			}
		});
	};

	var deleteReviewImage = function(code){
		if ( typeof images[code] != 'undefined' ) {
			delete images[code];
		}
		$("span._img_"+code).remove();
	};

	var openAddReview = function(idx,prod_code,prod_order_code){
		$.cocoaDialog.close();
		$('#myModalReview').modal("hide");//review_remind 모달 id분리로 인한 처리
		$.ajax({
			type : 'POST',
			data : {'idx' : idx,'prod_code' : prod_code, 'prod_order_code' : prod_order_code},
			url : ('/shop/open_add_review.cm'),
			dataType : 'json',
			cache : false,
			async : false,
			success : function(result){
				if(result.msg === 'SUCCESS'){
					$.cocoaDialog.open({type : 'add_review', custom_popup : result.html, 'close_block': true, width : 800, hide_event : function(){
							images = {};
						}});
				}else{
					alert(result.msg);
				}
			}
		});
	};

	var convertReviewImage = function(image_list){
		$review_form.find('._btn_add_image').toggleClass('no-margin', false);
		for(var i = 0; i < image_list.length; i++){
			images[i] = image_list[i];
			var html = '<span class="file-add _img_'+i+'"><input type="hidden" name="img" value="' + i + '"><img src="' + CDN_UPLOAD_URL + image_list[i] + '"><a class="del" href="javascript:;" onclick="SITE_SHOP_REVIEW.deleteReviewImage(\''+i+ '\')" ><i class="btm bt-times vertical-middle" aria-hidden="true"></i></a></span>';
			$review_form.find("#review_image_box ._btn_add_image").before(html);
		}
	};

	function resizeStickyContainer(){
		var s_top = $(this).scrollTop();
		if(isIOS && isSafari){
			$write_header.css({'-webkit-transition': 'top 100ms', 'transition': 'top 100ms', 'top': s_top + 'px'});
			$fr_m_custom.toggleClass('m_sticky_container', s_top > m_sticky_container_trigger_top);
			$fr_m_custom.toggleClass('m_sticky_container_ios', s_top > m_sticky_container_trigger_top);
			if(s_top > m_sticky_container_trigger_top){
				$toolbarContainer.css({'-webkit-transition': 'top 100ms', 'transition': 'top 100ms', 'top': s_top + 'px'});
				review_body.find('.fr-view').css('padding-top', '90px');
			}else{
				$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'top': 'auto'});
				review_body.find('.fr-view').css('padding-top', '');
			}
		}else{
			$fr_m_custom.toggleClass('m_sticky_container', s_top > m_sticky_container_trigger_top);
		}
		$toolbarContainer.find('.fr-toolbar').css('width', review_body.find('.fr-view').width());
		if($(window).width() >= 768){
			if($review_container.hasClass('bg_on'))
				$review_container.find('#toolbarContainer').toggleClass('pc_sticky_toolbar', s_top > 487);
			else
				$review_container.find('#toolbarContainer').toggleClass('pc_sticky_toolbar', s_top > 180);
		}
	}

	var initMobileReview = function(){
		$mobile_review_wrap = $('#prod_detail_content_mobile');
		$mobile_form = $mobile_review_wrap.find('#mobile_review_form');
		$m_rating = $mobile_form.find('#mobile_rating');
		$m_star = $mobile_form.find('._star');
		$mobile_form.find("#mobile_review_image_box").hide();

		$mobile_form.find('#mobile_review_image_upload_btn').setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#mobile_review_image_box").show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#mobile_review_image_box").append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
		autosize($('.textarea_block textarea'));
	};

	var changeRating = function(t, n){

		if(t == 'desktop'){
			$rating.val(n + 1);
			$star.each(function(e){
				if(n <= 0 && e == 0){
					if(n == -1){
						$(this).removeClass('active');
					}else{
						$(this).addClass('active');
					}
				}else{
					$(this).removeClass('active');
					if(e <= n){
						$(this).addClass('active');
					}
				}
			});
		}else{
			$m_rating.val(n + 1);
			$m_star.each(function(e){
				if(n <= 0 && e == 0){
					if(n == -1){
						$(this).removeClass('active');
					}else{
						$(this).addClass('active');
					}
				}else{
					$(this).removeClass('active');
					if(e <= n){
						$(this).addClass('active');
					}
				}
			});
		}
	};

	var reviewFormShow = function(t){
		var sub_form = $("._sub_form_" + t);

		sub_form.data('show', 'Y');
		sub_form.show();
		var comment_add_body = sub_form.find('._comment_add_body_' + t);

		$('body').off('mouseup.sub_comment')
			.on('mouseup.sub_comment', function(e){
				var $c_target = $(e.target);
				var $s_form = $c_target.closest('._sub_form_' + t + ', ._show_sub_form_btn_' + t);
				if($s_form.length == 0){

					var text = comment_add_body.val();
					sub_form.data('show', 'N');
					if(text == ''){
						$('body').off('mouseup.sub_comment');
						reviewFormHide();
					}
				}
			});
	};

	var reviewEditShow = function(t){
		var editor_form = $("._sub_form_editor_" + t);
		editor_form.siblings().hide();

		editor_form.data('show', 'Y');
		editor_form.show();
		autosize.update(editor_form.find('textarea'));

	};

	var reviewEditHide = function(t){
		var editor_form = $("._sub_form_editor_" + t);
		editor_form.hide();
	};

	var reviewFormHide = function(){
		$("._sub_review_form").hide();
	};

	var reviewDelete = function(t, c, r_p, only_photo, buyer_permission){
		only_photo = only_photo == 'Y' ? true : false;
		$.ajax({
			type : 'POST',
			data : {code : t, prod_code : c},
			url : ('/shop/check_review_point.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					var msg = buyer_permission == 'Y' ? LOCALIZE.설명_삭제하시겠습니까삭제후재등록불가() : LOCALIZE.설명_삭제하시겠습니까();
					if(res.alert_msg != '' && res.alert_msg != null) msg = res.alert_msg;
					if(confirm(msg)){
						$.ajax({
							type : 'POST',
							data : {code : t, prod_code : c, change_member_point : res.change_member_point},
							url : ('/shop/delete_review.cm'),
							dataType : 'json',
							success : function(result){
								if(result.msg == 'SUCCESS'){
									if(IS_MOBILE) SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.REVIEW, r_p);
									else SITE_SHOP_DETAIL.changeContentPCTab(CONST.TAB_TYPE.REVIEW, r_p, '', false, only_photo);
									$.cocoaDialog.close();
								}else
									alert(result.msg);
							}
						});
					}
				}else
					alert(res.msg);
			}
		});
	};

	var reviewHide = function(t, c,r_p) {
		if(confirm(LOCALIZE.설명_숨기시겠습니까())){
			$.ajax({
				type : 'POST',
				data : {code : t, prod_code : c , is_visible : false},
				url : ('/shop/delete_review.cm'),
				dataType : 'json',
				success : function(result){
					if(result.msg == 'SUCCESS'){
						if(IS_MOBILE) SITE_SHOP_DETAIL.changeContentTab(CONST.TAB_TYPE.REVIEW,r_p);
						else SITE_SHOP_DETAIL.changeContentPCTab(CONST.TAB_TYPE.REVIEW,r_p);
						$.cocoaDialog.close();
					}else
						alert(result.msg);
				}
			});
		}
	};

	var CheckSecret = function(code,secret_pass,callback){
		$.ajax({
			type:'post',
			data:{code:code,secret_pass :secret_pass, type : 'review'},
			url:'/ajax/check_review_pass.cm',
			dataType:'json',
			success:function(result){
				if(result.msg == 'SUCCESS'){
					if(typeof callback == 'function')
						callback();
				}else{
					alert(result.msg);
				}
			}
		});
	};
	var EditReviewShow = function(t,c,idx){
		var $show_secret_password = $('#show_secret_password');
		var $show_link = $(event.target);
		if($show_secret_password.length == 0){
			$show_secret_password = $('<div class="remove-pop" id="show_secret_password" onclick="event.cancelBubble = true;" tabindex="0" style="position:absolute;z-index:99999;"><p>'+LOCALIZE.설명_작성시등록하신비밀번호를입력해주세요()+'</p><div class="input_area"><input type="password" placeholder="'+LOCALIZE.설명_비밀번호()+'"><button class="btn btn-primary _confirm">'+LOCALIZE.버튼_확인닫기()+'</button></div></div>').hide();
			$show_link.after($show_secret_password);
		}
		$show_secret_password.find('input').val('');
		$show_secret_password.show();
		$show_secret_password.off('click', '._confirm')
			.on('click', '._confirm', function(){
				var secret_pass = $show_secret_password.find('input').val();
				CheckSecret(t,secret_pass,function(){
					SITE_SHOP_REVIEW.openAddReview(idx,c);
				});
				$show_secret_password.hide();
			});
		$('body').off('mousedown.show_secret')
			.on('mousedown.show_secret', function(e){
				var $tmp = $(e.target).closest('#show_secret_password');
				if($tmp.length == 0){
					$show_secret_password.hide();
					$('body').off('click.show_secret');
				}
			});
	};

	var reviewDeleteShow = function(t,c,only_photo){
		var $show_secret_password = $('#show_secret_password');
		var $show_link = $(event.target);
		if($show_secret_password.length == 0){
			$show_secret_password = $('<div class="remove-pop" id="show_secret_password" onclick="event.cancelBubble = true;" tabindex="0" style="position:absolute;z-index:99999;"><p>'+LOCALIZE.설명_작성시등록하신비밀번호를입력해주세요()+'</p><div class="input_area"><input type="password" placeholder="'+LOCALIZE.설명_비밀번호()+'"><button class="btn btn-primary _confirm">'+LOCALIZE.버튼_확인닫기()+'</button></div></div>').hide();
			$show_link.after($show_secret_password);
		}
		$show_secret_password.find('input').val('');
		$show_secret_password.show();
		$show_secret_password.off('click', '._confirm')
			.on('click', '._confirm', function(){
				var secret_pass = $show_secret_password.find('input').val();
				CheckSecret(t,secret_pass,function(){
					reviewDelete(t, c,'',only_photo);
				});
				$show_secret_password.hide();
			});
		$('body').off('mousedown.show_secret')
			.on('mousedown.show_secret', function(e){
				var $tmp = $(e.target).closest('#show_secret_password');
				if($tmp.length == 0){
					$show_secret_password.hide();
					$('body').off('click.show_secret');
				}
			});
	};

	var imageUploadInit = function(n){
		$("#sub_review_image_box_" + n).hide();
		//$("#editor_review_image_box_"+n).hide();

		$('#sub_review_image_upload_btn_' + n).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#sub_review_image_box_" + n).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.url + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#sub_review_image_box_" + n).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

		$('#editor_review_image_upload_btn_' + n).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#editor_review_image_box_" + n).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.url + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#editor_review_image_box_" + n).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
	};

	var submit = function(){
		if(is_submit) return false;
		is_submit = true;
		var data = $review_form.serializeObject();
		data.images = images;
		$.ajax({
			type : 'POST',
			data : data,
			url : ('/shop/add_review.cm'),
			dataType : 'json',
			success : function(res){
				if(res.msg == 'SUCCESS'){
					IMWEB_SESSIONSTORAGE.clear("PROD_REVIEW.*");
					reviewCompleted(res.received_point,res.check_received_point);
					if ( typeof CHANNEL_PLUGIN != "undefined") CHANNEL_PLUGIN.CompleteReview();
				}else{
					is_submit = false;
					alert(res.msg);
				}
			}
		});
	};

	var reviewCompleted = function(received_point,check_received_point){
		$.cocoaDialog.close();
		$.ajax({
			type: 'POST',
			data: {'received_point' : received_point, 'check_received_point' : check_received_point},
			url: ('/shop/add_review_completed.cm'),
			dataType: 'html',
			async: false,
			cache: false,
			success : function(html){
				$.cocoaDialog.open({
					type : 'prod_review_completed',
					custom_popup : html,
					'close_block' : false,
					hide_event : function(){
						location.reload();
					}
				});
			}
		});
	};

	var reviewCancel = function(){
		if(isIOS && isSafari){
			var s_top = $(this).scrollTop();
			$write_header.css({'-webkit-transition': 'none', 'transition': 'none', 'position': 'fixed', 'top': 0});
			$fr_m_custom.toggleClass('m_sticky_container', s_top > m_sticky_container_trigger_top);
			$fr_m_custom.toggleClass('m_sticky_container_ios', s_top > m_sticky_container_trigger_top);
			if(s_top > m_sticky_container_trigger_top){
				$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'position': 'fixed', 'top': $write_header.height() + 'px'});
			}else{
				$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'top': 'auto'});
			}
		}
		history.go(-1);
	};


	var createHtml = function(prod_idx, review_page, qna_page, paging_on,only_photo){
		$review_wrap = $('._review_wrap');
		if ( $review_wrap.length ) {
			review_page = review_page || 0;
			qna_page = qna_page || 0;

			var is_method_get = ( review_page == 0 && qna_page == 0 );

			$.ajax({
				type : ( is_method_get ? 'GET' : 'POST' ),
				data : {prod_idx : prod_idx, review_page : review_page, qna_page : qna_page, only_photo : only_photo},
				url : ('/shop/prod_review_pc_html.cm'),
				dataType : 'html',
				success : function(result){
					$review_wrap.html(result);
				}
			});
		}
	};

	var checkReviewData = function(){
		var check = Object.keys(images).length > 0 || review_body.val() != '';
		if(check){
			if(confirm(LOCALIZE.설명_작성한내용이모두사라집니다())){
				$.cocoaDialog.close();
			}else{
				return false;
			}
		}else{
			$.cocoaDialog.close();
		}
	};


	return {
		init : function(code){
			init(code);
		},
		deleteReviewImage : function(code){
			deleteReviewImage(code);
		},
		openAddReview : function(idx,prod_code,prod_order_code){
			return openAddReview(idx,prod_code,prod_order_code);
		},
		convertReviewImage : function(image_list){
			return convertReviewImage(image_list);
		},
		initMobileReview : function(){
			initMobileReview();
		},
		submit : function(){
			submit();
		},
		reviewCompleted : function(received_point,check_received_point){
			reviewCompleted(received_point,check_received_point);
		},
		reviewCancel : function(){
			reviewCancel();
		},
		changeRating : function(t, n){
			changeRating(t, n);
		},
		FormShow : function(t){
			reviewFormShow(t);
		},
		Delete : function(t, c,r_p,only_photo,buyer_permission){
			reviewDelete(t, c,r_p,only_photo,buyer_permission);
		},
		EditShow : function(t){
			reviewEditShow(t);
		},
		EditReviewShow : function(t,c,idx){
			EditReviewShow(t,c,idx);
		},
		DeleteShow : function(t,c,only_photo){
			reviewDeleteShow(t,c,only_photo);
		},
		Hide : function(t, c,r_p){
			reviewHide(t, c,r_p);
		},
		EditHide : function(t){
			reviewEditHide(t);
		},
		imageUploadInit : function(n){
			imageUploadInit(n);
		},
		createHtml : function(prod_idx, review_page, qna_page,paging_on,only_photo){
			createHtml(prod_idx, review_page, qna_page,paging_on,only_photo);
		},
		checkReviewData : function(){
			checkReviewData();
		}
	}
}();

var SHOP_REVIEW_COMMENT = function(){
	var $review_comment_wrap;
	var $form;
	var $secret;
	var review_code;
	var device;
	var load_type; // 아코디언인지, 모달인지 체크
	var init = function(code,type,device_type){
		device = device_type;
		load_type = type;
		$form = $('#' + device + "_" + load_type+'_review_form_'+code);
		review_code = code;
		$secret = $('._secret_btn');
		$secret.on('click', function(){
			if($secret.hasClass('active')){
				$secret.removeClass('active');
				$('#use_secret_review').val('N');
			}else{
				$secret.addClass('active');
				$('#use_secret_review').val('Y');
			}
		});

		$form.find('#review_image_upload_btn_'+code).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#review_comment_image_box_"+code).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.url + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#review_comment_image_box_"+code).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
	};

	var imageUploadInit = function(idx){
		$("#sub_review_image_box_" + idx).hide();

		$('#sub_review_image_upload_btn_' + idx).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#sub_review_image_box_" + idx).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.url + '"><img src="' +  url + '"><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#sub_review_image_box_" + idx).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

		$('#editor_review_image_upload_btn_' + idx).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#editor_review_image_box_" + idx).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.url + '"><img src="' + url + '"><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#editor_review_image_box_" + idx).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
	};

	var getReviewCommentHtml = function(code,type,object){
		if(type == 'accordion'){
			$review_comment_wrap = $('.review_comment_wrap_'+code);
			if(!object.hasClass('active')){
				$.ajax({
					type : 'POST',
					data : {'code' : code ,'type' : type},
					url : ('/shop/get_review_comment_list.cm'),
					dataType : 'json',
					async : false,
					cache : false,
					success : function(result){
						if(result.msg === 'SUCCESS'){
							$review_comment_wrap.html(result.html);
							$review_comment_wrap.show();
						}else{
							alert(result.msg);
						}
					}
				});
			}else{
				$review_comment_wrap.hide();
			}
		}else{
			$review_comment_wrap = $('#review_comment_section_'+code);
			$.ajax({
				type : 'POST',
				data : {'code' : code ,'type' : type},
				url : ('/shop/get_review_comment_list.cm'),
				dataType : 'json',
				async : false,
				cache : false,
				success : function(result){
					if(result.msg === 'SUCCESS'){
						$review_comment_wrap.html(result.html);
					}else{
						alert(result.msg);
					}
				}
			});
		}
	};
	var submit = function(t, type, i){
		switch(type){
			case 'main': // pc 리뷰 댓글
				var data = $form.serializeObject();
				break;
			case 'sub_form': //pc 리뷰 대댓글
				var data = $('#sub_review_form_' + i).serializeObject();
				break;
			case 'editor': // pc 리뷰 댓글 수정
				var data = $('#sub_review_editor_form_' + i).serializeObject();
				break;
			case 'mobile': // Mobile 리뷰 댓글
				var data = $('#mobile_review_form').serializeObject();
				break;
			case 'mobile_sub_form': // Mobile 리뷰 대댓글
				var data = $('#mobile_sub_review_form_' + i).serializeObject();
				break;
			case 'mobile_editor': // Mobile 리뷰 댓글 수정
				var data = $('#mobile_sub_review_editor_form_' + i).serializeObject();
				break;
		}
		if(!t.hasClass("btn-writing")){
			t.addClass("btn-writing");
		}
		$.ajax({
			type : 'POST',
			data : {data : data, review_code : review_code},
			url : ('/shop/add_review_comment.cm'),
			dataType : 'json',
			async : false,
			cache : false,
			success : function(result){
				if(t.hasClass("btn-writing")){
					t.removeClass("btn-writing");
				}
				if(result.msg == 'SUCCESS'){
					if(load_type == 'modal'){
						$('#comment_count').text(" "+result.comment_count);
						getReviewCommentHtml(review_code,'modal',t);
					}else{
						$('#comment_count_'+review_code).text(" "+result.comment_count);
						$('#comment_count_inner_'+review_code).text(" "+result.comment_count);
						getReviewCommentHtml(review_code,'accordion',t);
					}
					$("div[id^='sub_review_image_box_']").hide();
				}else
					alert(result.msg);
			}
		});
	};
	var EditHide = function(idx){
		var editor_form = $("._sub_form_editor_" + idx);
		editor_form.hide();
		$('.tools').show();
		editor_form.siblings('._comment_body').show();
		editor_form.closest('.comment_area').siblings().show();
		editor_form.closest('.comment_area').css('padding-top', '');
		editor_form.css('margin-top', '');
	};

	var EditShow = function(idx){
		var editor_form = $("._sub_form_editor_" + idx);
		editor_form.siblings().hide();
		editor_form.closest('.comment_area').siblings().hide();
		editor_form.closest('.comment_area').css('padding-top', 0);
		editor_form.css('margin-top', 0);
		editor_form.data('show', 'Y');
		editor_form.show();
		autosize.update(editor_form.find('textarea'));
	};

	var reviewFormHide = function(){
		$("._sub_review_form").hide();
	};

	var FormShow = function(idx){
		var sub_form = $("._sub_form_" + idx);

		sub_form.data('show', 'Y');
		sub_form.show();
		var comment_add_body = sub_form.find('._comment_add_body_' + idx);

		$('body').off('mouseup.sub_comment')
			.on('mouseup.sub_comment', function(e){
				var $c_target = $(e.target);
				var $s_form = $c_target.closest('._sub_form_' + idx + ', ._show_sub_form_btn_' + idx);
				if($s_form.length == 0){

					var text = comment_add_body.val();
					sub_form.data('show', 'N');
					if(text == ''){
						$('body').off('mouseup.sub_comment');
						reviewFormHide();
					}
				}
			});
	};

	var reviewCommentDelete = function(code){
		if(confirm(LOCALIZE.설명_삭제하시겠습니까())){
			$.ajax({
				type : 'POST',
				data : {code : code, review_code : review_code},
				url : ('/shop/delete_review_comment.cm'),
				async : false,
				dataType : 'json',
				success : function(result){
					if(result.msg == 'SUCCESS'){
						if(load_type == 'modal'){
							$('#comment_count').text(result.comment_count);
							getReviewCommentHtml(review_code,'modal',$(this));
						}else{
							$('#comment_count_'+review_code).text(result.comment_count);
							getReviewCommentHtml(review_code,'accordion',$(this));
						}

					}else
						alert(result.msg);
				}
			});
		}
	};
	return {
		init : function(code,type,device_type){
			init(code,type,device_type);
		},
		imageUploadInit : function(idx){
			imageUploadInit(idx);
		},
		getReviewCommentHtml : function(code,type,object){
			getReviewCommentHtml(code,type,object);
		},
		submit : function(t, type, i){
			submit(t, type, i);
		},
		Delete : function(code){
			reviewCommentDelete(code);
		},
		EditShow : function(idx){
			EditShow(idx);
		},
		EditHide : function(idx){
			EditHide(idx);
		},
		FormShow : function(idx){
			FormShow(idx);
		}
	}
}();
var SITE_QNA_COMMENT = function(){
	var $form;
	var $secret;
	var $qna_comment_section;
	var qna_code;

	var init = function(code){
		qna_code = code;
		var $comment_area = $('.comment_textarea');
		$secret = $('._secret_btn');
		$secret.on('click', function(){
			if($secret.hasClass('active')){
				$secret.removeClass('active');
				$('#use_secret_qna').val('N');
			}else{
				$secret.addClass('active');
				$('#use_secret_qna').val('Y');
			}
		});

		$form = $('#qna_form');
		$form.find('#qna_image_upload_btn').setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#qna_image_box").show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#qna_image_box").append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

	};

	var QnaCommentDelete = function(code,is_visible){
		if(confirm(LOCALIZE.설명_삭제하시겠습니까())){
			$.ajax({
				type : 'POST',
				data : {code : code,is_visible : is_visible},
				url : ('/shop/delete_qna_comment.cm'),
				async : false,
				dataType : 'json',
				success : function(result){
					if(result.msg == 'SUCCESS'){
						getQnaCommentHtml(qna_code);
					}else
						alert(result.msg);
				}
			});
		}
	};

	var imageUploadInit = function(idx){
		$form = $('#qna_form');
		$form.find('#qna_image_upload_btn').setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#qna_image_box").show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#qna_image_box").append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

		$("#sub_qna_image_box_" + idx).hide();

		$('#sub_qna_image_upload_btn_' + idx).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#sub_qna_image_box_" + idx).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#sub_qna_image_box_" + idx).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

		$('#editor_qna_image_upload_btn_' + idx).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#editor_qna_image_box_" + idx).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#editor_qna_image_box_" + idx).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
	};


	var getQnaCommentHtml = function(qna_code){
		$qna_comment_section = $('#qna_comment_section');
		$.ajax({
			type : 'POST',
			data : {qna_code : qna_code},
			url : ('/ajax/get_qna_comment_list.cm'),
			dataType : 'html',
			cache : false,
			success : function(result){
				$qna_comment_section.html(result);
			}
		});
	};
	var submit = function(t, type, i){
		switch(type){
			case 'main': // 1:1 문의 페이지에서 바로 작성하는 폼
				var data = $form.serializeObject();
				break;
			case 'sub_form': // 등록되어 있는 qna에 답변을 다는 폼
				var data = $('#sub_qna_form_' + i).serializeObject();
				break;
			case 'editor': // 등록되어 있는 qna를 수정하는 폼
				var data = $('#sub_qna_editor_form_' + i).serializeObject();
				break;
		}
		if(!t.hasClass("btn-writing")){
			t.addClass("btn-writing");
		}
		$.ajax({
			type : 'POST',
			data : {data : data, type : type},
			url : ('/shop/add_qna_comment.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(t.hasClass("btn-writing")){
					t.removeClass("btn-writing");
				}
				if(result.msg == 'SUCCESS'){
					getQnaCommentHtml(qna_code);
					$("div[id^='sub_qna_image_box_']").hide();
				}else
					alert(result.msg);
			}
		});
	};
	var EditShow = function(idx){
		var editor_form = $("._sub_form_editor_" + idx);
		editor_form.siblings().hide();
		editor_form.closest('.comment_area').siblings().hide();
		editor_form.closest('.comment_area').css('padding-top', 0);
		editor_form.css('margin-top', 0);
		editor_form.data('show', 'Y');
		editor_form.show();
		autosize.update(editor_form.find('textarea'));

	};

	var EditHide = function(idx){
		var editor_form = $("._sub_form_editor_" + idx);
		editor_form.hide();
		$('.tools').show();
		editor_form.siblings('._comment_body').show();
		editor_form.closest('.comment_area').siblings().show();
		editor_form.closest('.comment_area').css('padding-top', '');
		editor_form.css('margin-top', '');
	};

	var qnaFormHide = function(){
		$("._sub_qna_form").hide();
	};

	var FormShow = function(idx){
		var sub_form = $("._sub_form_" + idx);

		sub_form.data('show', 'Y');
		sub_form.show();
		var comment_add_body = sub_form.find('._comment_add_body_' + idx);

		$('body').off('mouseup.sub_comment')
			.on('mouseup.sub_comment', function(e){
				var $c_target = $(e.target);
				var $s_form = $c_target.closest('._sub_form_' + idx + ', ._show_sub_form_btn_' + idx);
				if($s_form.length == 0){

					var text = comment_add_body.val();
					sub_form.data('show', 'N');
					if(text == ''){
						$('body').off('mouseup.sub_comment');
						qnaFormHide();
					}
				}
			});
	};
	return {
		init : function(code){
			init(code);
		},
		imageUploadInit : function(idx){
			imageUploadInit(idx);
		},
		getQnaCommentHtml : function(qna_code){
			getQnaCommentHtml(qna_code);
		},
		submit : function(t, type, i){
			submit(t, type, i);
		},
		Delete : function(code,is_visible){
			QnaCommentDelete(code,is_visible);
		},
		EditShow : function(idx){
			EditShow(idx);
		},
		EditHide : function(idx){
			EditHide(idx);
		},
		FormShow : function(idx){
			FormShow(idx);
		}
	}
}();

var SITE_SHOP_QNA = function(){
	var $qna_wrap;
	var $mobile_qna_wrap;
	var $form;
	var $mobile_form;
	var $comment_body;
	var $qna_image_box;
	var $comment_area;
	var $secret;
	var $m_secret;
	var qna_body;
	var $qna_container;
	var $qna_form;
	var body_input;
	var $show_secret_password;
	var isIOS, isSafari, $fr_m_custom, $write_header, m_sticky_container_trigger_top, $toolbarContainer;

	var init = function(code,qna_secret_type){
		$qna_wrap = $('._qna_wrap');
		$qna_form = $('#qna_form');
		qna_body = $('#qna_body');
		$qna_container = $('#qna_container');
		$secret = $('._secret_btn');
		$qna_form.find("#qna_image_box").hide();
		body_input = $('#body_input');
		if(qna_secret_type == 'secret'){
			$secret.addClass('active');
			$('._secret').val('Y');
		}else{
			$secret.on('click', function(){
				if($secret.hasClass('active')){
					$secret.removeClass('active');
					$('._secret').val('N');
				}else{
					$secret.addClass('active');
					$('._secret').val('Y');
				}
			});
		}
		if($('._secret').val() != ''){//수정일 경우 비밀글 체크
			if($('._secret').val() == 'Y'){
				$secret.addClass('active');
				$('._secret').val('Y');
			}else{
				$secret.removeClass('active');
				$('._secret').val('N');
			}
		}


		if(IE_VERSION < 10){
			CKEDITOR.replace( 'qna_body',{
				filebrowserImageUploadUrl: '/ajax/post_image_upload.cm?board_code='+code
			});
		}else{
			if(android_version() == 4){
				qna_body.addClass('legacy_webview');
			}
			var image_insert_key2 = 'image_insert_key2';
			if(FROALA_VERSION >= 300){
				setFroala('#qna_body', {
					code : '',
					// 파일 첨부, 비디오, 테이블 제외
					toolbarButtons : {
						'moreText': {
							'buttons': ['bold', 'italic', 'underline', 'fontSize', 'textColor', 'strikeThrough', 'backgroundColor', 'clearFormatting', '|', 'align', 'formatOL', 'formatUL', '|', image_insert_key2, 'insertLink', '|', 'undo', 'redo', 'html'],
							'buttonsVisible': 50
						}
					},
					toolbarButtonsMobile : {
						'moreText': {
							'buttons': [image_insert_key2, 'insertLink', '|', "fontSize", "bold", "italic", "underline", "strikeThrough", "align", "textColor", "clearFormatting", '|', "formatOL", "formatUL"],
							'buttonsVisible': 50
						}
					},
					image_upload_url : "/ajax/post_image_upload.cm",
					image_insert_key : image_insert_key2,
					image_align : 'center',
					image_display : 'block',
					toolbarStickyOffset : 38,
					heightMin: 200,
					heightMax: 600,
					mobile_custom: true
				})
			}else{
				qna_body.setFroala({
					code : '',
					image_upload_url : "/ajax/post_image_upload.cm",
					toolbarButtons  : ["bold", "italic", "underline", "strikeThrough", "fontFamily", "fontSize", '|', "clearFormatting", "color", "align", "formatOL", "formatUL", "insertHR", '|', 'insertLink', image_insert_key2,  'html'],
					toolbarButtonsMD: ["bold", "italic", "underline", "strikeThrough", "fontFamily", "fontSize", '|', "clearFormatting", "color", "align", "formatOL", "formatUL", "insertHR", '|', 'insertLink', image_insert_key2,  'html'],
					toolbarButtonsSM: ["bold", "italic", "underline", "strikeThrough", "fontFamily", "fontSize", '|', "clearFormatting", "color", "align", "formatOL", "formatUL", "insertHR", '|', 'insertLink', image_insert_key2,  'html'],
					toolbarButtonsXS : [image_insert_key2, 'insertLink', '|', "fontSize", "bold", "italic", "underline", "strikeThrough", "align", "color", "clearFormatting", '|', 'quote', "insertHR", "formatOL", "formatUL"],
					image_insert_key : image_insert_key2,
					image_align : 'center',
					image_display : 'block',
					toolbarStickyOffset : 38,
					heightMin: 200,
					heightMax: 600,
					mobile_custom: true
				});
			}

		}

		isIOS = /(iPad|iPhone|iPod)/g.test(navigator.userAgent);
		isSafari = /^((?!chrome|android).)*safari/i.test(navigator.userAgent);
		$fr_m_custom = $qna_container.find('._fr-m-custom');
		$write_header = $qna_container.find('._write_header');
		m_sticky_container_trigger_top = $fr_m_custom.offset().top - $fr_m_custom.height();
		$toolbarContainer = $fr_m_custom.find('#toolbarContainer');
		if(isIOS && isSafari){
			$write_header.css('position', 'absolute');
		}
		var timeoutTime = isIOS && isSafari ? 100 : 10;
		var resize_time;
		resizeStickyContainer();
		$(window).off('scroll.mobile_write resize.mobile_write').on('scroll.mobile_write resize.mobile_write',function(){
			var s_top = $(this).scrollTop();
			if(isIOS && isSafari){
				$write_header.css({'-webkit-transition': 'none', 'transition': 'none', 'top': 0});
				if(s_top > m_sticky_container_trigger_top){
					$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'top': 0});
				}
			}
			if(resize_time) {
				clearTimeout(resize_time);
			}
			resize_time = setTimeout(function() {
				resizeStickyContainer();
			}, timeoutTime);
		});
		autosize($('.textarea_block textarea'));
	};

	function resizeStickyContainer(){
		var s_top = $(this).scrollTop();
		if(isIOS && isSafari){
			$write_header.css({'-webkit-transition': 'top 100ms', 'transition': 'top 100ms', 'top': s_top + 'px'});
			$fr_m_custom.toggleClass('m_sticky_container', s_top > m_sticky_container_trigger_top);
			$fr_m_custom.toggleClass('m_sticky_container_ios', s_top > m_sticky_container_trigger_top);
			if(s_top > m_sticky_container_trigger_top){
				$toolbarContainer.css({'-webkit-transition': 'top 100ms', 'transition': 'top 100ms', 'top': s_top + 'px'});
				qna_body.find('.fr-view').css('padding-top', '90px');
			}else{
				$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'top': 'auto'});
				qna_body.find('.fr-view').css('padding-top', '');
			}
		}else{
			$fr_m_custom.toggleClass('m_sticky_container', s_top > m_sticky_container_trigger_top);
		}
		$toolbarContainer.find('.fr-toolbar').css('width', qna_body.find('.fr-view').width());
		if($(window).width() >= 768){
			if($qna_container.hasClass('bg_on'))
				$qna_container.find('#toolbarContainer').toggleClass('pc_sticky_toolbar', s_top > 487);
			else
				$qna_container.find('#toolbarContainer').toggleClass('pc_sticky_toolbar', s_top > 180);
		}
	}

	var initMobileQna = function(){
		$mobile_qna_wrap = $('#prod_detail_content_mobile');
		$mobile_form = $mobile_qna_wrap.find('#mobile_qna_form');
		$m_secret = $mobile_form.find('._secret_btn');
		$mobile_form.find("#mobile_qna_image_box").hide();

		$m_secret.on('click', function(){
			if($m_secret.hasClass('active')){
				$m_secret.removeClass('active');
				$mobile_form.find('._secret').val('N');
			}else{
				$m_secret.addClass('active');
				$mobile_form.find('._secret').val('Y');
			}
		});

		$mobile_form.find('#mobile_qna_image_upload_btn').setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#mobile_qna_image_box").show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#mobile_qna_image_box").append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
		autosize($('.textarea_block textarea'));
	};

	var qnaFormShow = function(t){
		var sub_form = $("._sub_form_" + t);

		sub_form.data('show', 'Y');
		sub_form.show();
		var comment_add_body = sub_form.find('._comment_add_body_' + t);

		$('body').off('mouseup.sub_comment')
			.on('mouseup.sub_comment', function(e){
				var $c_target = $(e.target);
				var $s_form = $c_target.closest('._sub_form_' + t + ', ._show_sub_form_btn_' + t);
				if($s_form.length == 0){

					var text = comment_add_body.val();
					sub_form.data('show', 'N');
					if(text == ''){
						$('body').off('mouseup.sub_comment');
						qnaFormHide();
					}
				}
			});
	};

	var EditQnaShow = function(t,c,idx){
		$show_secret_password = $('#show_secret_password');
		var $show_link = $(event.target);
		if($show_secret_password.length == 0){
			var left = $(window).width() >= 768 ? 200 : ($(window).width() - 325) / 2;
			$show_secret_password = $('<div class="remove-pop" id="show_secret_password" tabindex="0" style="position:absolute;left:' + left +'px;top:80px;z-index:99999;"><p>'+LOCALIZE.설명_작성시등록하신비밀번호를입력해주세요()+'</p><div class="input_area"><input type="password" placeholder="'+LOCALIZE.설명_비밀번호()+'"><button class="btn btn-primary _confirm">'+LOCALIZE.버튼_확인닫기()+'</button></div></div>').hide();
			$show_link.after($show_secret_password);
		}
		$show_secret_password.find('input').val('');
		$show_secret_password.show();
		$show_secret_password.off('click', '._confirm')
			.on('click', '._confirm', function(){
				var secret_pass = $show_secret_password.find('input').val();
				CheckSecret(t,secret_pass,function(){
					window.location.href = "?prod_code="+c+"&qmode=write&back_url=&idx="+idx;
				});
				$show_secret_password.hide();
			});
		$('body').off('mousedown.show_secret')
			.on('mousedown.show_secret', function(e){
				var $tmp = $(e.target).closest('#show_secret_password');
				if($tmp.length == 0){
					$show_secret_password.hide();
					$('body').off('click.show_secret');
				}
			});
	};

	var ViewQnaShow = function(t,c,idx,qna_page){
		$show_secret_password = $('#show_secret_password');
		var $show_link = $(event.target);
		if($show_secret_password.length == 0){
			var left = $(window).width() >= 768 ? 200 : ($(window).width() - 325) / 2;
			$show_secret_password = $('<div class="remove-pop" id="show_secret_password" tabindex="0" style="position:absolute;left:' + left +'px;top:80px;z-index:99999;"><p>'+LOCALIZE.설명_작성시등록하신비밀번호를입력해주세요()+'</p><div class="input_area"><input type="password" placeholder="'+LOCALIZE.설명_비밀번호()+'"><button class="btn btn-primary _confirm">'+LOCALIZE.버튼_확인닫기()+'</button></div></div>').hide();
			$show_link.after($show_secret_password);
		}
		$show_secret_password.find('input').val('');
		$show_secret_password.show();
		$show_secret_password.off('click', '._confirm')
			.on('click', '._confirm', function(){
				var secret_pass = $show_secret_password.find('input').val();
				CheckSecret(t,secret_pass,function(){
					SITE_SHOP_DETAIL.viewQnaDetail(idx,qna_page);
					$show_secret_password.hide();
				});
				$show_secret_password.hide();
			});
		$('body').off('mousedown.show_secret')
			.on('mousedown.show_secret', function(e){
				var $tmp = $(e.target).closest('#show_secret_password');
				if($tmp.length == 0){
					$show_secret_password.hide();
					$('body').off('click.show_secret');
				}
			});
	};

	var CheckSecret = function(code,secret_pass,callback){
		$.ajax({
			type:'post',
			data:{code:code,secret_pass :secret_pass,type : 'qna'},
			url:'/ajax/check_review_pass.cm',
			dataType:'json',
			success:function(result){
				if(result.msg == 'SUCCESS'){
					if(typeof callback == 'function')
						callback();
				}else{
					alert(result.msg);
				}
			}
		});
	};

	var qnaEditShow = function(t){
		var editor_form = $("._sub_form_editor_" + t);
		editor_form.siblings().hide();

		editor_form.data('show', 'Y');
		editor_form.show();
		autosize.update(editor_form.find('textarea'));

	};

	var qnaEditHide = function(t){
		var editor_form = $("._sub_form_editor_" + t);
		editor_form.hide();
	};

	var qnaFormHide = function(){
		$("._sub_qna_form").hide();
	};

	var qnaDelete = function(code, prod_code, secret_pass,q_p){
		if(confirm(LOCALIZE.설명_삭제하시겠습니까())){
			$.ajax({
				type : 'POST',
				data : {code : code, prod_code : prod_code, secret_pass : secret_pass,qna_page:q_p},
				url : ('/shop/delete_qna.cm'),
				dataType : 'json',
				success : function(result){
					if(result.msg == 'SUCCESS'){
						if(IS_MOBILE) SITE_SHOP_DETAIL.changeContentTab('qna',q_p);
						else SITE_SHOP_DETAIL.changeContentPCTab('qna',q_p);
						if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.addCountUserProfileAttr('qnaCount', -1);
						$.cocoaDialog.close();
					}else
						alert(result.msg);
				}
			});
		}
	};

	var qnaModify = function(idx, prod_code, secret_pass, is_book,code){
		$.ajax({
			type : 'POST',
			data : {idx : idx, prod_code : prod_code, secret_pass : secret_pass, is_book : is_book, code : code},
			url : ('/shop/show_secret_qna.cm'),
			dataType : 'json',
			success : function(result){
				if(result.msg == 'SUCCESS'){
					qnaEditShow(idx);
				}else
					alert(result.msg);
			}
		});
	};

	var qnaShow = function(idx, prod_code, secret_pass, is_book,code){
		$.ajax({
			type : 'POST',
			data : {idx : idx, prod_code : prod_code, secret_pass : secret_pass, is_book : is_book, code : code},
			url : ('/shop/show_secret_qna.cm'),
			dataType : 'json',
			success : function(result){
				if(result.msg == 'SUCCESS'){
					$("._comment_body_"+idx).html(result.html);
					$('._comment_body_' + idx).closest('.comment_area').find('#_show_content_btn').hide();		// 보기 버튼 제거
					if(result.isSubComment){
						for(var i in result.sub_comment){
							var sub_data = result.sub_comment[i];
							$("._comment_child_"+idx+"_"+sub_data.idx).html(sub_data.html);
						}
					}
				}else
					alert(result.msg);
			}
		});
	};

	var qnaConfirmShow = function (e, idx, prod_code, type , code){
		$show_secret_password = $('#show_secret_password');
		var $show_link = $(event.target);
		if($show_secret_password.length == 0){
			var left = $(window).width() >= 768 ? 200 : ($(window).width() - 325) / 2;
			$show_secret_password = $('<div class="remove-pop" id="show_secret_password" tabindex="0" style="position:absolute;left:' + left +'px;top:80px;z-index:99999;"><p>'+LOCALIZE.설명_작성시등록하신비밀번호를입력해주세요()+'</p><div class="input_area"><input type="password" placeholder="'+LOCALIZE.설명_비밀번호()+'"><button class="btn btn-primary _confirm">'+LOCALIZE.버튼_확인닫기()+'</button></div></div>').hide();
			$show_link.after($show_secret_password);
		}

		$show_secret_password.find('input').val('');
		$show_secret_password.show();
		$show_secret_password.off('click', '._confirm')
			.on('click', '._confirm', function(){
				var secret_pass = $show_secret_password.find('input').val();
				$show_secret_password.hide();
				switch(type){
					case 'show' :
						qnaShow(idx, prod_code, secret_pass, 'N',code);
						break;
					case 'modify' :
						qnaModify(idx, prod_code, secret_pass, 'N',code);
						break;
					case 'delete' :
						qnaDelete(code, prod_code, secret_pass);
						break;

				}
			});
		$('body').off('mousedown.show_secret')
			.on('mousedown.show_secret', function(e){
				var $tmp = $(e.target).closest('#show_secret_password');
				if($tmp.length == 0){
					$show_secret_password.hide();
					$('body').off('click.show_secret');
				}
			});
	};

	var imageUploadInit = function(n){
		$("#sub_qna_image_box_" + n).hide();

		$('#sub_qna_image_upload_btn_' + n).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#sub_qna_image_box_" + n).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#sub_qna_image_box_" + n).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

		$('#editor_qna_image_upload_btn_' + n).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#editor_qna_image_box_" + n).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#editor_qna_image_box_" + n).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
	};

	var submit = function(){
		if(IE_VERSION < 10){
			var body = CKEDITOR.instances.qna_body.getData();
			body_input.val(body);
			$qna_form.submit();
		}else{
			if(FROALA_VERSION >= 300){
				if(qna_body.hasClass('fr-code-view'))
					FroalaEditor('#qna_body').codeView.toggle();
				var body = FroalaEditor('#qna_body').html.get(true);
			}else{
				if(qna_body.hasClass('fr-code-view'))
					qna_body.froalaEditor('codeView.toggle');
				var body = qna_body.froalaEditor("html.get", true, true);
			}
			body_input.val(body);
			$qna_form.submit();
		}
	};

	var qnaCancel = function(){
		if(isIOS && isSafari){
			var s_top = $(this).scrollTop();
			$write_header.css({'-webkit-transition': 'none', 'transition': 'none', 'position': 'fixed', 'top': 0});
			$fr_m_custom.toggleClass('m_sticky_container', s_top > m_sticky_container_trigger_top);
			$fr_m_custom.toggleClass('m_sticky_container_ios', s_top > m_sticky_container_trigger_top);
			if(s_top > m_sticky_container_trigger_top){
				$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'position': 'fixed', 'top': $write_header.height() + 'px'});
			}else{
				$toolbarContainer.css({'-webkit-transition': 'none', 'transition': 'none', 'top': 'auto'});
			}
		}
		history.go(-1);
	};

	var createHtml = function(prod_idx, review_page, qna_page, paging_on){
		$qna_wrap = $('._qna_wrap');
		if ( $qna_wrap.length ) {
			var is_cache = false;
			var callback = function(result){
				$qna_wrap.html(result);
				if ( ! is_cache ) IMWEB_SESSIONSTORAGE.set("PROD_QNA_PC_" + prod_idx + "_" + review_page + '_' + qna_page, result.replace(/\t+/g, '').trim(), 60);
			};

			var html = IMWEB_SESSIONSTORAGE.get("PROD_QNA_PC_" + prod_idx + "_" + review_page + '_' + qna_page);
			if ( html ) {
				is_cache = true;
				callback(html);
			} else {
				$.ajax({
					type : 'POST',
					data : {prod_idx : prod_idx, review_page : review_page, qna_page : qna_page},
					url : ('/shop/prod_qna_pc_html.cm'),
					dataType : 'html',
					cache : false,
					success : callback
				});
			}
		}
	};

	var qnaHide = function(t,c,q_p){
		if(confirm(LOCALIZE.설명_숨기시겠습니까())){
			$.ajax({
				type : 'POST',
				data : {code : t, prod_code : c, is_visible : false},
				url : ('/shop/delete_qna.cm'),
				dataType : 'json',
				cache : false,
				success : function(result){
					if(result.msg == 'SUCCESS'){
						if(IS_MOBILE) SITE_SHOP_DETAIL.changeContentTab('qna', q_p);
						else SITE_SHOP_DETAIL.changeContentPCTab('qna', q_p);
						if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.addCountUserProfileAttr('qnaCount',-1);
						$.cocoaDialog.close();
					}else
						alert(result.msg);
				}
			});
		}
	};

	var qnaDeleteShow = function(qna_code, prod_code){
		var $show_secret_password = $('#show_secret_password');
		var $show_link = $(event.target);
		if($show_secret_password.length == 0){
			var left = $(window).width() >= 768 ? 200 : ($(window).width() - 325) / 2;
			$show_secret_password = $('<div class="remove-pop" id="show_secret_password" tabindex="0" style="position:absolute;left:' + left +'px;top:80px;z-index:99999;"><p>'+LOCALIZE.설명_작성시등록하신비밀번호를입력해주세요()+'</p><div class="input_area"><input type="password" placeholder="'+LOCALIZE.설명_비밀번호()+'"><button class="btn btn-primary _confirm">'+LOCALIZE.버튼_확인닫기()+'</button></div></div>').hide();
			$show_link.after($show_secret_password);
		}
		$show_secret_password.find('input').val('');
		$show_secret_password.show();
		$show_secret_password.off('click', '._confirm')
			.on('click', '._confirm', function(){
				var secret_pass = $show_secret_password.find('input').val();
				CheckSecret(qna_code, secret_pass, function(){
					qnaDelete(qna_code, prod_code, secret_pass);
				});
				$show_secret_password.hide();
			});
		$('body').off('mousedown.show_secret')
			.on('mousedown.show_secret', function(e){
				var $tmp = $(e.target).closest('#show_secret_password');
				if($tmp.length == 0){
					$show_secret_password.hide();
					$('body').off('click.show_secret');
				}
			});
	};


	return {
		init : function(code,qna_secret_type){
			init(code,qna_secret_type);
		},
		initMobileQna : function(){
			initMobileQna();
		},
		submit : function(){
			submit();
		},
		qnaCancel : function(){
			qnaCancel();
		},
		FormShow : function(t){
			qnaFormShow(t);
		},
		EditQnaShow : function(t,c,idx){
			EditQnaShow(t,c,idx);
		},
		ViewQnaShow : function(t,c,idx,qna_page){
			ViewQnaShow(t,c,idx,qna_page);
		},
		Delete : function(code, prod_code,q_p){
			qnaDelete(code, prod_code,q_p);
		},
		EditShow : function(t){
			qnaEditShow(t);
		},
		EditHide : function(t){
			qnaEditHide(t);
		},
		imageUploadInit : function(n){
			imageUploadInit(n);
		},
		createHtml : function(prod_idx, review_page, qna_page,paging_on){
			createHtml(prod_idx, review_page, qna_page,paging_on);
		},
		confirmShow : function(e, idx, prod_code, type, code){
			qnaConfirmShow(e, idx, prod_code, type, code);
		},
		Hide : function(t,c,q_p){
			qnaHide(t,c,q_p);
		},
		DeleteShow : function(qna_code, prod_code){
			qnaDeleteShow(qna_code, prod_code);
		},
	}
}();

var SITE_PERSONAL_QNA = function(){
	var $personal_qna_wrap;
	var $mobile_qna_wrap;
	var $form;
	var $mobile_form;
	var $comment_body;
	var $qna_image_box;


	var $comment_area;
	var $secret;

	var init = function(){
		$form = $('#qna_form');
		$secret = $form.find('._secret');

		$secret.on('click', function(){
			if($secret.hasClass('active')){
				$secret.removeClass('active');
				$form.find('#secret').val('N');
			}else{
				$secret.addClass('active');
				$form.find('#secret').val('Y');
			}
		});

		$('#qna_image_upload_btn').setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#qna_image_box").show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#qna_image_box").append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
		autosize($('.textarea_block textarea'));
	};

	var submit = function(t, type, i){
		switch(type){
			case 'main': // 1:1 문의 페이지에서 바로 작성하는 폼
				var data = $form.serializeObject();
				break;
			case 'sub_form': // 등록되어 있는 qna에 답변을 다는 폼
				var data = $('#sub_qna_form_' + i).serializeObject();
				break;
			case 'editor': // 등록되어 있는 qna를 수정하는 폼
				var data = $('#sub_qna_editor_form_' + i).serializeObject();
				break;
		}
		if(!t.hasClass("btn-writing")){
			t.addClass("btn-writing");
		}
		$.ajax({
			type : 'POST',
			data : {data : data, type : type, personal_qna : 'Y'},
			url : ('/shop/add_qna.cm'),
			dataType : 'json',
			cache : false,
			success : function(result){
				if(t.hasClass("btn-writing")){
					t.removeClass("btn-writing");
				}
				if(result.msg == 'SUCCESS'){
					createHtml(result.page);
				}else
					alert(result.msg);
			}
		});
	};

	var Delete = function(code, prod_code){
		if(confirm(LOCALIZE.설명_삭제하시겠습니까())){
			$.ajax({
				type : 'POST',
				data : {code : code, prod_code : prod_code},
				url : ('/shop/delete_qna.cm'),
				dataType : 'json',
				success : function(result){
					if(result.msg == 'SUCCESS'){
						if ( typeof CHANNEL_PLUGIN != 'undefined' ) CHANNEL_PLUGIN.addCountUserProfileAttr('qnaCount',-1);
						createHtml();
					}else
						alert(result.msg);
				}
			});
		}
	};

	var FormShow = function(t){
		var sub_form = $("._sub_form_" + t);

		sub_form.data('show', 'Y');
		sub_form.show();
		var comment_add_body = sub_form.find('._comment_add_body_' + t);

		$('body').off('mouseup.sub_comment')
			.on('mouseup.sub_comment', function(e){
				var $c_target = $(e.target);
				var $s_form = $c_target.closest('._sub_form_' + t + ', ._show_sub_form_btn_' + t);
				if($s_form.length == 0){

					var text = comment_add_body.val();
					sub_form.data('show', 'N');
					if(text == ''){
						$('body').off('mouseup.sub_comment');
						FormHide();
					}
				}
			});
	};

	var FormHide = function(){
		$("._sub_qna_form").hide();
	};

	var EditShow = function(t){
		var editor_form = $("._sub_form_editor_" + t);
		editor_form.siblings('.block-postmeta').find('.write').hide();
		editor_form.siblings('.block-postmeta').find('.comment_area').hide();
		editor_form.data('show', 'Y');
		editor_form.show();
	};

	var EditHide = function(t){
		var editor_form = $("._sub_form_editor_" + t);
		editor_form.hide();
		editor_form.siblings('.block-postmeta').find('.write').show();
		editor_form.siblings('.block-postmeta').find('.comment_area').show();
	};

	var imageUploadInit = function(n){
		$("#sub_image_box_" + n).hide();

		$('#sub_image_upload_btn_' + n).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#sub_image_box_" + n).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#sub_image_box_" + n).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});

		$('#editor_image_upload_btn_' + n).setUploadImage({
			url : '/shop/upload_image.cm',
			dropZone : 'icon_img_upload_wrap',
			singleFileUploads : true,
			formData : {temp : 'Y'}
		}, function(res, data){
			$("#editor_image_box_" + n).show();
			$.each(data, function(e, tmp){
				if(tmp.error == "" || tmp.error == null){
					var url = CDN_UPLOAD_URL + tmp.url;
					var html = '<span class="file-add"><input type="hidden" name="img" value="' + tmp.name + '"><div class="file-add-bg" style="background: url('+url+') no-repeat center center;"></div><em class="del" onclick="POST_COMMENT.removeCommentImg($(this))"></em></span>';
					$("#editor_image_box_" + n).append(html);
				}else{
					alert(tmp.error);
				}
			});
		});
	};

	var createHtml = function(page){
		$personal_qna_wrap = $('._personal_qna_wrap');
		$.ajax({
			type : 'POST',
			data : {page:page},
			url : ('/shop/personal_qna_list.cm'),
			dataType : 'html',
			cache : false,
			success : function(result){
				$personal_qna_wrap.html(result);
			}
		});
	};



	return{
		init : function(){
			init();
		},
		submit : function(t, type, i){
			submit(t, type, i);
		},
		Delete : function(code, prod_code){
			Delete(code, prod_code);
		},
		FormShow : function(t){
			FormShow(t);
		},
		EditShow : function(t){
			EditShow(t);
		},
		EditHide : function(t){
			EditHide(t);
		},
		imageUploadInit : function(n){
			imageUploadInit(n);
		},
		createHtml : function(page){
			createHtml(page);
		}
	}
}();