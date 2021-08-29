var SITE_SHOP_DETAIL = function(){
    var CONST = {
        WIDTH_MOBILE: 768,
        TAB_TYPE: {
        DETAIL: 'detail',
        REVIEW: 'review',
        RETURN: 'return'      
        }
    };

    var $body;
    var $selected_options;
    var $prod_detail;
    var $prod_detail;
    var $prod_detail_content;
    
    



    var increaseOrderCount = function(type, success){
        var o = $prod_detail.find('input._order_count_' + type);
        var curCount = o.val();
        if(isNaN(curCount))
        curCount = 1;

        else
            curCount = parseInt(curCount) + 1;

        var checkProdStockResult = checkProdStock(curCount);
        if( checkProdStockResult === true) {
            order_count = curCount;
        } else {
            alert(checkProdStockResult);
        }
        o.val(order_count);
        success();
    };

    // 옵션선택 화면 갱신
    // type : prod(상품상세화면)이랑 (장바구니 수량 변경시)
    var updateSelectedOptions = function(type) {
        var html = '';
        var total_price_html = '';

        var total_price = 0;
        var total_count = 0;
        var option_total_price = 0;
        // 이부분은...
        var period_discount_price = 0;

        if(require_option_count == 0) {
            total_count = order_count;
            total_price += order_count * period_discount_price;
            period_discount_price += calcPeriodDiscount(prod_price, order_count);
            if(period_discount_price > total_price) period_discount_price = total_price;
            
            //필수 옵션이 없을때 기본 상품 수량 조절 html출력
            // if(!is_digital_prod){
            //     switch(type){
            //         case 'prod':
            //             if(!is_view_price) break;
            //             //상품 상세 페이지
            //             // html += 
            //     }
            // }

        }
    }


}
