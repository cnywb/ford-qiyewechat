var Nav = function () {
	
	return { init: init };
	
	function init () {
		
		$('.p-dashboard .nav-dashboard').addClass('active');
		$('.p-battery .nav-battery').addClass('active');
			$('.p-battery-freq .nav-battery-freq').addClass('active');
			$('.p-battery-banner .nav-battery-banner').addClass('active');
	    $('.p-activity .nav-activity').addClass('active');
	    	$('.p-activity-type .nav-activity-type').addClass('active');
			$('.p-activity-cgz .nav-activity-cgz').addClass('active');
			$('.p-activity-ggk .nav-activity-ggk').addClass('active');
			$('.p-activity-dzp .nav-activity-dzp').addClass('active');
			$('.p-activity-qhb .nav-activity-qhb').addClass('active');
			$('.p-activity-qwdy .nav-activity-qwdy').addClass('active');
			$('.p-activity-wilm .nav-activity-wilm').addClass('active');
			$('.p-activity-yyy .nav-activity-yyy').addClass('active');
			$('.p-activity-zzz .nav-activity-zzz').addClass('active');
			$('.p-activity-qygl .nav-activity-qygl').addClass('active');
			$('.p-activity-welcome .nav-activity-welcome').addClass('active');
			$('.p-activity-task .nav-activity-task').addClass('active');
			$('.p-activity-log .nav-activity-log').addClass('active');
			$('.p-activity-auth .nav-activity-auth').addClass('active');

		$('.p-textkey .nav-textkey').addClass('active');
			$('.p-textkey-list .nav-textkey-list').addClass('active');
			$('.p-textkey-question .nav-textkey-question').addClass('active');
			$('.p-textkey-guaguaka .nav-textkey-guaguaka').addClass('active');
			$('.p-textkey-coupon .nav-textkey-coupon').addClass('active');
			$('.p-message-hello .nav-message-hello').addClass('active');
			$('.p-message-list .nav-message-list').addClass('active');
			$('.p-inte-bycar .nav-inte-bycar').addClass('active');
			$('.p-member-mark .nav-member-mark').addClass('active');
		$('.p-audit .nav-audit').addClass('active');
			$('.p-audit-view .nav-audit-view').addClass('active');
			$('.p-audit-act .nav-audit-act').addClass('active');
			$('.p-audit-banner .nav-audit-banner').addClass('active');
			$('.p-audit-banner-charg .nav-audit-banner-charg').addClass('active');
			$('.p-inte-validate .nav-inte-validate').addClass('active');
			$('.p-admin-dtmenu .nav-admin-dtmenu').addClass('active');
			$('.p-resource .nav-resource').addClass('active');
			$('.p-resource-photo .nav-resource-photo').addClass('active');
			$('.p-notice-send .nav-notice-send').addClass('active');
		$('.p-inte .nav-inte').addClass('active');
			$('.p-inte-gain .nav-inte-gain').addClass('active');
			$('.p-inte-source .nav-inte-source').addClass('active');
			$('.p-inte-change .nav-inte-change').addClass('active');
			$('.p-inte-product .nav-inte-product').addClass('active');
			$('.p-inte-task .nav-inte-task').addClass('active');

		$('.p-member .nav-member').addClass('active');
			$('.p-member-interaction .nav-member-interaction').addClass('active');
			$('.p-member-fans .nav-member-fans').addClass('active');
		$('.p-priv .nav-priv').addClass('active');
			$('.p-admin-user .nav-admin-user').addClass('active');
			$('.p-admin-role .nav-admin-role').addClass('active');
			$('.p-admin-menu .nav-admin-menu').addClass('active');
			$('.p-admin-product .nav-admin-product').addClass('active');
		$('.p-report .nav-report').addClass('active');
			$('.p-report-statis .nav-report-statis').addClass('active');
			$('.p-report-isp .nav-report-isp').addClass('active');
			$('.p-report-version .nav-report-version').addClass('active');
			$('.p-report-plat .nav-report-plat').addClass('active');
			$('.p-report-hdcity .nav-report-hdcity').addClass('active');
			
		
		var mainnav = $('.main-nav'),
			openActive = mainnav.is ('.open-active'),
			navActive = mainnav.find ('> .active');

		mainnav.find ('> .dropdown > a').bind ('click', navClick);

		if (openActive && navActive.is ('.dropdown')) {			
			navActive.addClass ('opened').find ('.sub-nav').show ();
		}
	}
	
	function navClick (e) {
		e.preventDefault ();
		var li = $(this).parents ('li');		
		if (li.is ('.opened')) { 
			closeAll ();
		} else { 
			closeAll ();
			li.addClass ('opened').find ('.sub-nav').slideDown ();
		}
	}

	function closeAll () {	
		$('.sub-nav').slideUp ().parents ('li').removeClass ('opened');
	}
}();

var App = function () {
	"use strict";	
	return { init: init,  debounce: debounce };
	function init () {
		initLayout ();	
		initAutosize ();
		initFormValidation ();
		initSelect2 ();		
		initDatepicker ();
		initTimepicker ();
		initTooltips ();
		initICheck ();
		initTableCheckable ();
		//initDataTableHelper ();
	}

	function initLayout () {
		Nav.init ();	
		$('body').on('touchstart.dropdown', '.dropdown-menu', function (e) { 
		    e.stopPropagation(); 
		});
		function bodySM (){
			if ($(window).width() < 1024 ) {
				$('body').addClass('p-sm');
				$('.sidebar').addClass('sidebar-sm');
				$('.content').addClass('content-sm');
			} else {
				$('body').removeClass('p-sm');
				$('.sidebar').removeClass('sidebar-sm');
				$('.content').removeClass('content-sm');
			}		
		};
		bodySM();
		$(window).on('resize', bodySM);
	}

	function initAutosize () {
		if ($.fn.autosize) {
		$('.ui-textarea-autosize').each(function() {
			if($(this).data ('animate')) {
					$(this).addClass ('autosize-animate').autosize();
				} else {
					$(this).autosize();
				}
			});
		}
	}

	function initFormValidation () {
		if ($.fn.parsley) {
			$('.parsley-form').each (function () {
				$(this).parsley ({
					trigger: 'blur',
					validationMinlength: 0 ,
					errors: {
						container: function (element, isRadioOrCheckbox) {
							if (element.parents ('form').is ('.form-horizontal')) {
								return element.parents ("div[class^='col-']");
							}
							return element.parents ('.form-group');
						}
					},
					messages: {
					defaultMessage: "您的输入有误"
						, type: {
							email:      "请输入有效的电子邮件地址"
							, url:        "请输入有效的网络地址"
							, urlstrict:  "请输入有效的网络地址"
							, number:     "只允许输入数字"
							, digits:     "请输入数字"
							, dateIso:    "正确的日期格式为YYYY-MM-DD"
							, alphanum:   "只允许输入字母和数字"
							, phone:      "请输入有效的电话号码"
						}
						, notnull:        "必填项"
						, notblank:       "输入不能为空"
						, required:       "必填项"
						, regexp:         "输入的值不合法"
						, min:            "最小值为 %s"
						, max:            "允许的最大值为 %s"
						, range:          "%s - %s 位字符"
						, minlength:      "最少 %s 位字符"
						, maxlength:      "最多 %s 位字符"
						, rangelength:    "%s - %s 位字符"
						, mincheck:       "至少选择 %s 项"
						, maxcheck:       "最多选择 %s 项"
						, rangecheck:     "请选择 %s - %s 项"
						, equalto:        "两次输入不一致"
					}
				});
			});
			$('.modal').on('hidden.bs.modal', function () {
				  $('.modal .parsley-form').parsley().reset();
			})				
		}
	}

	function initSelect2 () {
		if ($.fn.select2) {
			$('.select2-input').select2({ allowClear: true, placeholder: "Select..." });
		}
	}

	function initDatepicker () {
		if ($.fn.datepicker) { $('.ui-datepicker').datepicker ({ autoclose: true, todayHighlight: true }); }
	}

	function initTimepicker () {
		if ($.fn.timepicker) { 
			var pickers = $('.timepicker, .ui-timepicker, .ui-timepicker-modal');

			pickers.each (function () {
				$(this).parent ('.input-group').addClass ('bootstrap-timepicker');

				if ($(this).is ('.timepicker')) {
					$(this).timepicker ({ use24hours: true });
				} else {
					$(this).timepicker({ template: 'modal' });
				}	
			});		
		}
	}

	function initTooltips () {
		if ($.fn.tooltip) { $('.ui-tooltip').tooltip (); }
		if ($.fn.popover) { $('.ui-popover').popover ({ container: 'body' }); }
	}

	function initICheck () {
		if ($.fn.iCheck) {
			$('.icheck-input').iCheck({
				checkboxClass: 'ui-checkbox',
				radioClass: 'ui-radio',
				inheritClass: true
			}).on ('ifChanged', function (e) {
				$(e.currentTarget).trigger ('change');
			});
			$('.table-checkable tbody tr').on('click',function(){
				if ( $(this).children().hasClass('checkbox-column') ) {
					if ($(this).hasClass('checked')) {
						$(this).removeClass('checked').find('.icheck-input').removeAttr('checked').iCheck('update');
						$(this).parents('.table-checkable').find('thead .icheck-input').removeAttr('checked').iCheck('update');
					} else {
						$(this).addClass('checked').find('.icheck-input').prop('checked', true).iCheck('update');
						var hasChecbox = $(this).siblings().not('.checked').find('[type="checkbox"]');
						var hasRadio = $(this).siblings().find('[type="radio"]');
						if ( hasChecbox.length <= 0 ) {
							$(this).parents('.table-checkable').find('thead .icheck-input').prop('checked',true).iCheck('update');
						}						
						if ( hasRadio.length > 0 ) {
							$(hasRadio).parents('.table-checkable tbody tr').removeClass('checked');
							$(hasRadio).removeAttr('checked').iCheck('update');
						}						
					};
				}	else {
					$(this).addClass('checked');
					$(this).siblings().removeClass('checked');					
				}					
			})
		}
	}

	function initTableCheckable () {
		if ($.fn.tableCheckable) {
			$('.table-checkable')
		        .tableCheckable ()
			        .on ('masterChecked', function (event, master, slaves) { 
			            if ($.fn.iCheck) { $(slaves).iCheck ('update'); }
			        })
			        .on ('slaveChecked', function (event, master, slave) {
			            if ($.fn.iCheck) { $(master).iCheck ('update'); }
			        });
		}
	}

	function initDataTableHelper () {
		if ($.fn.dataTable) {
			$('[data-provide="datatable"]').each (function () {	
				$(this).addClass ('dataTable-helper');		
				var defaultOptions = {
						paginate: true,
						search: false,
						info: true,
						lengthChange: true,
						displayRows: 10
					},
					dataOptions = $(this).data (),
					helperOptions = $.extend (defaultOptions, dataOptions),
					$thisTable,
					tableConfig = {};

				tableConfig.iDisplayLength = helperOptions.displayRows;
				tableConfig.bFilter = true;
				tableConfig.bSort = true;
				tableConfig.bPaginate = false;
				tableConfig.bLengthChange = false;	
				tableConfig.bInfo = false;

				if (helperOptions.paginate) { tableConfig.bPaginate = true; }
				if (helperOptions.lengthChange) { tableConfig.bLengthChange = true; }
				if (helperOptions.info) { tableConfig.bInfo = true; }       
				if (helperOptions.search) { $(this).parent ().removeClass ('datatable-hidesearch'); }				

				tableConfig.aaSorting = [];
				tableConfig.aoColumns = [];

				$(this).find ('thead tr th').each (function (index, value) {
					var sortable = ($(this).data ('sortable') === true) ? true : false;
					tableConfig.aoColumns.push ({ 'bSortable': sortable });

					if ($(this).data ('direction')) {
						tableConfig.aaSorting.push ([index, $(this).data ('direction')]);
					}
				});		
				
				// Create the datatable
				$thisTable = $(this).dataTable (tableConfig);

				if (!helperOptions.search) {
					$thisTable.parent ().find ('.dataTables_filter').remove ();
				}

				var filterableCols = $thisTable.find ('thead th').filter ('[data-filterable="true"]');

				if (filterableCols.length > 0) {
					var columns = $thisTable.fnSettings().aoColumns,
						$row, th, $col, showFilter;

					$row = $('<tr>', { cls: 'dataTable-filter-row' }).appendTo ($thisTable.find ('thead'));

					for (var i=0; i<columns.length; i++) {
						$col = $(columns[i].nTh.outerHTML);
						showFilter = ($col.data ('filterable') === true) ? 'show' : 'hide';

						th = '<th class="' + $col.prop ('class') + '">';
						th += '<input type="text" class="form-control input-sm ' + showFilter + '" placeholder="' + $col.text () + '">';
						th += '</th>';
						$row.append (th);
					}

					$row.find ('th').removeClass ('sorting sorting_disabled sorting_asc sorting_desc sorting_asc_disabled sorting_desc_disabled');

					$thisTable.find ('thead input').keyup( function () {
						$thisTable.fnFilter( this.value, $thisTable.oApi._fnVisibleToColumnIndex( 
							$thisTable.fnSettings(), $thisTable.find ('thead input[type=text]').index(this) ) );
					});

					$thisTable.addClass ('datatable-columnfilter');
				}
			});

			$('.dataTables_filter input').prop ('placeholder', 'Search...');
		}
	}

	function debounce (func, wait, immediate) {
		var timeout, args, context, timestamp, result;
		return function() {
			context = this;
			args = arguments;
			timestamp = new Date();

			var later = function() {
				var last = (new Date()) - timestamp;

				if (last < wait) {
					timeout = setTimeout(later, wait - last);
				} else {
					timeout = null;
					if (!immediate) result = func.apply(context, args);
				}
			};

			var callNow = immediate && !timeout;

			if (!timeout) {
				timeout = setTimeout(later, wait);
			}

			if (callNow) result = func.apply(context, args);
			return result;
		};
	}
}();

//Bootstrap 3 Modal Vertically Centered http://cdpn.io/mKfCc
function initModalCentered (){
	$('.modal').each(function(){
	    if($(this).hasClass('in') == false){
	      $(this).show();
	    };
	    var contentHeight = $(window).height() - 60;
	    var headerHeight = $(this).find('.modal-header').outerHeight() || 2;
	    var footerHeight = $(this).find('.modal-footer').outerHeight() || 2;

	    $(this).find('.modal-content').css({
	      'max-height': function () {
	        return contentHeight;
	      }
	    });

	    $(this).find('.modal-body').css({
	      'max-height': function () {
	        return contentHeight - (headerHeight + footerHeight);
	      }
	    });

	    $(this).find('.modal-dialog').addClass('modal-dialog-center').css({
	      'margin-top': function () {
	        return -($(this).outerHeight() / 2);
	      },
	      'margin-left': function () {
	        return -($(this).outerWidth() / 2);
	      }
	    });
	    if($(this).hasClass('in') == false){
	      $(this).hide();
	    };
	});
};
if ($(window).height() >= 320){
  $(window).resize(initModalCentered).trigger("resize");
}

$(function () {
	App.init ();
	initModalCentered();
});
