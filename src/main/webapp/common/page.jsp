<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/4
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script id="paginateTemplate" type="x-tmpl-mustache">
<div class="col-xs-6">
    <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">
        总共 {{total}} 中的 {{from}} ~ {{to}}
    </div>
</div>

<div class="col-xs-6">
    <div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
        <ul class="pagination">
            <li class="paginate_button previous {{^firstUrl}}disabled{{/firstUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="1" data-url="{{firstUrl}}" class="page-action">首页</a>
            </li>
            <li class="paginate_button {{^beforeUrl}}disabled{{/beforeUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="{{beforePageNo}}" data-url="{{beforeUrl}}" class="page-action">前一页</a>
            </li>
            <li class="paginate_button active" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-id="{{pageNo}}" >第{{pageNo}}页</a>
                <input type="hidden" class="pageNo" value="{{pageNo}}" />
            </li>
            <li class="paginate_button {{^nextUrl}}disabled{{/nextUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="{{nextPageNo}}" data-url="{{nextUrl}}" class="page-action">后一页</a>
            </li>
            <li class="paginate_button next {{^lastUrl}}disabled{{/lastUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="{{maxPageNo}}" data-url="{{lastUrl}}" class="page-action">尾页</a>
            </li>
        </ul>
    </div>
</div>
</script>

<script type="text/javascript">
    var paginateTemplate = $("paginateTemplate").html();
    Mustache.parse(paginateTemplate);

    /**
     *
     * @param url : 请求的链接
     * @param total : 当前满足的行数
     * @param pageNo : 当前是多少页
     * @param pageSize : 每页显示多少行数
     * @param currentSize : 当前这一页返回结果多少条
     * @param idElement :
     * @param callback :
     */
    function renderPage(url, total, pageNo, pageSize, currentSize, idElement, callback){
        // 当前最大的页数
        var maxPageNo = Math.ceil(total / pageSize);
        // 开始的值，如果url是以？结尾的话，代表有参数了。 有参数的话 用&符号拼接，没有的话用？做开始
        var paramStartChar = url.indexOf("?") > 0 ? "&" : "?";
        // 从多少条 from …… to ……
        var from = (pageNo - 1) * pageSize + 1;
        // 定义一个对象，把分页需要用到的参数都放进去
        var view = {
            from: from > total ? total : from,
            to: (from + currentSize - 1) > total ? total : (from + currentSize - 1),
            total: total,
            maxPageNo: maxPageNo,
            nextPageNo: pageNo >= maxPageNo ? maxPageNo : (pageNo + 1),
            beforePageNo: pageNo == 1 ? 1 :(pageNo - 1),
            firstUrl: (pageNo == 1) ? '' : (url + paramStartChar + "pageNo=1&pageSize=" + pageSize),
            beforeUrl: (pageNo == 1) ? '' : (url + paramStartChar + "pageNo=" + (pageNo - 1) + "&pageSize" + pageSize),
            nextUrl: (pageNo >= maxPageNo) ? '' : (url + paramStartChar + "pageNo=" + (pageNo + 1) + "&pageSize" + pageSize),
            lastUrl: (pageNo >= maxPageNo) ? '' : (url + paramStartChar + "pageNo=" + maxPageNo + "&pageSize" + pageSize)
        }
        // 将界面元素绑定上去
        $("#" + idElement).html(Mustache.render(paginateTemplate, view));

        $(".page-action").click(function(e){
            e.preventDefault();
            $("#" + idElement + " .pageNo").val($(this).attr("data-target"));
            var targetUrl = $(this).attr("data-url");
            if(targetUrl != ''){
                $.ajax({
                    url : tergetUrl,
                    success : function(result){
                        if(callback){
                            callback(result, url);
                        }
                    }
                })
            }
        })
    }
</script>