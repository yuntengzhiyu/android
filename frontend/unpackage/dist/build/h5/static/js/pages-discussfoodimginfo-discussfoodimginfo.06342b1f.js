(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-discussfoodimginfo-discussfoodimginfo"],{4085:function(t,n,i){var e=i("24fb");n=e(!1),n.push([t.i,'@charset "UTF-8";\r\n/**\r\n * 这里是uni-app内置的常用样式变量\r\n *\r\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\r\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\r\n *\r\n */\r\n/**\r\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\r\n *\r\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\r\n */\r\n/* 颜色变量 */\r\n/* 行为相关颜色 */\r\n/* 文字基本颜色 */\r\n/* 背景颜色 */\r\n/* 边框颜色 */\r\n/* 尺寸变量 */\r\n/* 文字尺寸 */\r\n/* 图片尺寸 */\r\n/* Border Radius */\r\n/* 水平间距 */\r\n/* 垂直间距 */\r\n/* 透明度 */\r\n/* 文章场景相关 */*[data-v-3119a485]{padding:0;margin:0;box-sizing:border-box;list-style:none}ul[data-v-3119a485]{display:-webkit-box;display:-webkit-flex;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;flex-direction:column}ul li[data-v-3119a485]{display:-webkit-box;display:-webkit-flex;display:flex;position:relative;-webkit-box-orient:horizontal;-webkit-box-direction:normal;-webkit-flex-direction:row;flex-direction:row;margin-top:%?10?%;border:2px solid #eee}ul li img[data-v-3119a485]{width:%?350?%;height:%?350?%;padding:%?20?%}ul li uni-text[data-v-3119a485]{display:block;width:%?350?%;font-size:%?35?%}',""]),t.exports=n},4402:function(t,n,i){"use strict";var e,r=function(){var t=this,n=t.$createElement,i=t._self._c||n;return i("v-uni-view",[i("span",{staticStyle:{"font-size":"40rpx",color:"orange"}},[t._v(t._s(t.title))]),i("span",[t._v("的做法步骤")]),i("ul",t._l(t.other,(function(n){return i("li",[i("img",{attrs:{src:n.img,alt:""}}),i("v-uni-text",[t._v(t._s(n.word))])],1)})),0)])},o=[];i.d(n,"b",(function(){return r})),i.d(n,"c",(function(){return o})),i.d(n,"a",(function(){return e}))},"4a9b":function(t,n,i){"use strict";i.r(n);var e=i("4402"),r=i("94f0");for(var o in r)"default"!==o&&function(t){i.d(n,t,(function(){return r[t]}))}(o);i("d4ac");var a,s=i("f0c5"),d=Object(s["a"])(r["default"],e["b"],e["c"],!1,null,"3119a485",null,!1,e["a"],a);n["default"]=d.exports},"566e":function(t,n,i){var e=i("4085");"string"===typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);var r=i("4f06").default;r("4b45069e",e,!0,{sourceMap:!1,shadowMode:!1})},"94f0":function(t,n,i){"use strict";i.r(n);var e=i("fad1"),r=i.n(e);for(var o in e)"default"!==o&&function(t){i.d(n,t,(function(){return e[t]}))}(o);n["default"]=r.a},d4ac:function(t,n,i){"use strict";var e=i("566e"),r=i.n(e);r.a},fad1:function(t,n,i){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var e={data:function(){return{other:[],title:""}},methods:{},onLoad:function(t){var n=this;uni.showLoading({title:"正在加载中"}),uni.request({url:this.baseURL+"getDiscussFoodImgInfo?id="+t.id,success:function(t){n.other=t.data.data.other,n.title=t.data.data.title}})},onReady:function(){uni.hideLoading()}};n.default=e}}]);