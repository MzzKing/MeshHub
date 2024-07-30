//
// //移入鼠标自动切换图片
// $(function() {
//     $('#left>li,#right>li').mouseenter(function() {
//         var i = $(this).index(); //index() 方法返回指定元素相对于其他指定元素的 index 位置。
//         if($(this).parent().attr("id")=="right"){
//             i+=4;
//         }//当前元素的父元素id是为右边无序列表id的话索引值加4,parent() 方法返回被选元素的直接父元素,attr() 方法设置或返回被选元素的属性和值
//         $("#center>li:not()").hide().eq(i).show();  //因为所有的图片都加在了中间无序列表里里，所有需要先隐藏全部图片后显示需要的图片,:not(selector)去除所有与给定选择器匹配的元素，hide() 方法隐藏被选元素,:eq(index) 选取索引等于index 的元素，索引从0开始,show() 方法显示隐藏的被选元素。
//     });//#left>li选择器是获取左边的<li>标签，同理#right>li获取的是右边的<li>标签，mouseenter是鼠标移入被选元素触发的事件
// }); //因为HTML文档是由浏览器从上到下依次载入的，载入事件能避免JavaScript代码放在太后面而没效果
//鼠标悬停在图片上，图片由中心放大，鼠标移开恢复原来大小
$(function(){

    $w = $('.pic').width();
    $h = $('.pic').height();
    $w2 = $w + 20;
    $h2 = $h + 20;

    $('.pic').hover(function(){
        $(this).stop().animate({height:$h2,width:$w2,left:"-10px",top:"-10px"},500);
    },function(){
        $(this).stop().animate({height:$h,width:$w,left:"0px",top:"0px"},500);
    });
});