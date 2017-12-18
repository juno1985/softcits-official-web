//等同于 $(document).ready(function(){}) --页面加载完毕后执行的函数
(function($){
	$.ajaxCheck = function(data) {
		//0-false
		if(data.result) return true;
		else {
			alert(data.msg);
			return false;
		}
	}
	//$.fn是jquery的命名空间，会对jquery每一个实例有效
	$.fn.mysorttable = function(opts) {
		var _isSort = false;
		var sortEle = $(this).find("tbody");
		var _that = this;
		var setting = $.extend({
			begin:"beginOrder",
			save:"saveOrder"
		},opts||{});
		sortEle.sortable({
			axis:"y",
			helper:function(e,ele) {
				//原始元素的td对象
				var _original = ele.children();
				var _helper = ele.clone();
				_helper.children().each(function(index){
					$(this).width(_original.eq(index).width());
				});
				_helper.css("background","#aaf");
				return _helper;
			},
			update:function(e,ui) {
				setOrders();
			}
		});
		
		sortEle.sortable("disable");
		
		$("#"+setting.begin).click(beginOrders);
		
		$("#"+setting.save).click(saveOrders);
		
		function beginOrders() {
			if(!_isSort) {
				$(_that).find("thead tr").append("<td>序号</td>");
				setOrders();
				$(_that).find("tfoot tr").append("<td>拖动排序</td>");
				sortEle.sortable("enable");
				_isSort = true;
			} else {
				alert("已经处于排序状态");
			}
		}
		
		function saveOrders() {
			if(_isSort) {
				//得到以表格id为参数的字符串 ids=33&ids=32&ids=34&ids=35&ids=36
				var idArg = sortEle.sortable("serialize",{key:"ids"});
			//	alert(idArg);
				$.post("updateSort?"+idArg,function(data){
					if($.ajaxCheck(data)) {
						$(_that).find("tr").each(function(){
							$(this).children().last().remove();
						});
						sortEle.sortable("disable");
						_isSort = false;
					}
				});
			} else {
				alert("还不是排序状态!");
			}
		}
		
		function setOrders() {
			$(_that).find("tbody tr").each(function(index){
				if(_isSort) {
					$(this).find("td:last").html((index+1));
				} else
					$(this).append("<td>"+(index+1)+"</td>");
			});
		}
		return sortEle;
	}
	$.fn.mytree = function(opts) {
		var setting = $.extend({
			data:{
				simpleData:{
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: -1
				}
			},
			view: {
				dblClickExpand: false,
				selectedMulti: false
			},
			async: {
				enable: true,
				url: opts?(opts.url||"treeAll"):"treeAll"
				
			},
			mine: {
				listChild:1,
				srcElement:"#cc"
			},
			callback:{
				onAsyncSuccess:function(){
					if(opts.mine.expandAll)
						t.expandAll(true);
				}
			}
		},opts||{});
		var _mine = setting.mine;
		var t = $.fn.zTree.init($(this), setting);
		t.getCheckParentNodes = getCheckParentNodes;
		t.getCheckChildNodes = getCheckChildNodes;
		//对于数字类型,if(a)语句中的a为0时if(a)为假,非0时if(a)为真
		if(_mine.listChild) {
			t.setting.callback.onClick = listChild;
		}
		//设定树节点点击在IFRAME里联动
		function listChild(event,treeId,treeNode) {
			$(_mine.srcElement).attr("src","channels/"+treeNode.id);
		}
		
		function getCheckParentNodes(treeNode,checked) {
			var ps = new Array();
			var pn;
			//通过循环遍历逐级向上寻找父节点
			while((pn=treeNode.getParentNode())) {
				if(pn.checked==checked) ps.push(pn);
				treeNode = pn;
			}
			return ps;
		}
		//第三个参数存储所有子节点
		function getCheckChildNodes(treeNode,checked,cs) {
			var ccs;
			//子节点是JSON存储的
			if((ccs=treeNode.children)) {
				for(var i=0;i<ccs.length;i++) {
					var c = ccs[i];
					if(c.checked==checked) {
						cs.push(c);
					}
					//通过迭代逐级向下搜寻子节点
					getCheckChildNodes(c,checked,cs);
				}
			}
		}
		return t;
	}
	
	$.fn.myaccordion = function(opts) {
		var settings = $.extend({
			selectedClz:"navSelected",
			titleTagName:"h3"
		},opts||{});
		var titleNode = $(this).find("ul>"+settings.titleTagName);
		var selectedNode = $(this).find("ul."+settings.selectedClz+">"+settings.titleTagName);
		//鼠标悬浮变手形
		titleNode.css("cursor","pointer");
		//未有navSelected属性的收起
		titleNode.nextAll().css("display","none");
		//有navSelected属性的的下拉
		selectedNode.nextAll().css("display","block");
		titleNode.click(function(){
			var checked = $(this).parent().hasClass(settings.selectedClz);
			if(checked) {
				$(this).parent().removeClass(settings.selectedClz);
				$(this).nextAll().slideUp();
			} else {
				$(this).parent().addClass(settings.selectedClz);
				$(this).nextAll().slideDown();
			}
		});
	};
	
	$.fn.trColorChange = function(opts) {
		var settings = $.extend({
			overClz:"trMouseover",
			evenClz:"trEvenColor"
		},opts||{});
		$(this).find("tbody tr:even").addClass(settings.evenClz);
		$(this).find("tbody tr").on("mouseenter mouseleave",function(){
			$(this).toggleClass(settings.overClz);
		});
	};
	
	$.fn.confirmOperator = function(opts) {
		var settings = $.extend({
			msg:"该操作不可逆，确定进行该操作吗？",
			eventName:"click"
		},opts||{});
		$(this).on(settings.eventName,function(event){
			if(!confirm(settings.msg)) {
				event.preventDefault();
			}
		});
	}
})(jQuery)