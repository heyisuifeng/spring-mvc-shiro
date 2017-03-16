var roleListTable = function () {

    var dataTable;
    var zTreeObj;
    var $table = $("#dataTable");
    var zNodes;
    /**
     * dataTable事件初始化方法
     */
    var handleRecords = function () {
        dataTable = new Datatable();
        dataTable.init({
            src: $table,
            onQuery: function (data) {
                data.roleName = $("#roleNameQuery").val().trim();
                data.description = $("#descriptionQuery").val().trim();
            },
            onSortColumn: function (sortColumn, sortDirection) {
                switch (sortColumn) {
                    case "roleName":
                        sortColumn = "role_name";
                        break;
                }
                return customGlobal.onSortColumnDefault(sortColumn, sortDirection);
            },
            dataTable: {
                "ajax": {
                    "url": basePath + "security/role/getRoleListPage" // ajax source
                },
                "columns": [
                    {data: 'roleName', orderable: true, searchable: true},
                    {data: 'description', orderable: true, searchable: true},
                    {data: 'operate', orderable: false,
                        render: function (data, type, full) {
                            return '<a class="edit btn default btn-xs purple" roleId="' + full.roleId + '"><i class="fa fa-edit"></i>'+i18n["edit"]+'</a>&nbsp;'
                                + '<a class="delete btn default btn-xs black" data-target="#confirmDialog" data-toggle="modal"><i class="fa fa-times"></i>'+i18n["delete"]+'</a>&nbsp;';
                        }
                    }
                ]
            }
        });
    };

    var handleEvent = function () {
        $("#addRole").on("click", function () {
            $("#modalTitle").html(i18n['addRole']);
            customGlobal.clearFormAndShowDialog("modalDialog");
            zTreeInit();
            $('#usernameList').select2();
            $("#dialogForm").validate({
                rules: {
                    roleName: {
                        required: true
                    }
                }
            }).resetForm();

            $("#passwordHelpBlock").html("");
            $("#addBtn").show();
            $("#updateBtn").hide();
        });

        function getPermTokens() {
            var nodes = zTreeObj.getCheckedNodes();
            //去掉重复的permToken
            var permTokenObj = {};
            for (var node in nodes) {
                if (nodes[node].permToken != "") {
                    permTokenObj[nodes[node].permToken] = nodes[node].permToken;
                }
            }
            var permTokens = [];
            for (node in permTokenObj) {
                permTokens.push(permTokenObj[node]);
            }
            return permTokens;
        }

        $("#addBtn").on("click", function () {

            if ($("#dialogForm").validate().form()) {
                customGlobal.blockUI("#modalContent");
                $.ajax({
                    url: "security/role",
                    data: JSON.stringify({
                        roleName: $("#roleName").val(),
                        description: $("#description").val(),
                        userList: $("#usernameList").val(),
                        permTokenList: getPermTokens()
                    }),

                    contentType: "application/json; charset=utf-8",
                    type: "post",
                    success: function (data) {
                        if (customGlobal.ajaxCallback(data)) {
                            $("#modalDialog").modal("hide");
                            dataTable.reloadTable();
                        }
                    }
                });
            }
        });

        $table.on("click", "a.edit", function () {
            $("#modalTitle").html(i18n['editRole']);
            customGlobal.clearFormAndShowDialog("modalDialog");
            $("#dialogForm").validate({
                rules: {
                    roleName: {
                        required: true
                    }
                }
            }).resetForm();

            $("#addBtn").hide();
            $("#updateBtn").show();
            $.get("security/role/" + $(this).attr("roleId"), function (data) {

                var role = data.returnData.role;
                $("#roleId").val(role.roleId);
                $("#roleName").val(role.roleName);
                $("#description").val(role.description);
                $("#passwordHelpBlock").html("");
                var $usernameList = $("#usernameList");
                $usernameList.find("option").removeAttr("selected");
                var usernameList = data.returnData.usernameList;

                for (var i = 0; i < usernameList.length; i++) {
                    var str = "option[value=" + usernameList[i].userId + "]";
                    $usernameList.find(str).attr("selected", "selected")
                }
                $usernameList.select2();
                zTreeInit(data.returnData.permTokenTree);
            });
        });

        $("#updateBtn").on("click", function () {
            if ($("#dialogForm").validate().form()) {
                customGlobal.blockUI("#modalContent");
                var data = {
                    roleId: $("#roleId").val(),
                    roleName: $("#roleName").val(),
                    description: $("#description").val(),
                    userList: $("#usernameList").val(),
                    permTokenList: getPermTokens()
                };

                $.ajax({
                    url: "security/role",
                    data: JSON.stringify(data),
                    type: "put",
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        if (customGlobal.ajaxCallback(data)) {
                            $("#modalDialog").modal("hide");
                            dataTable.reloadTable();
                        }
                    }
                });
            }
        });

        $table.on("click", "a.delete", function () {
            var $this = $(this);
            //confirm中确认按钮事件，此处需要unbind，否则点击取消时下次再点击删除按钮会重复绑定click。
            $("#confirmBtn").off("click.deleteRow").on("click.deleteRow", function () {
                $.ajax({
                    url: "security/role/" + $table.DataTable().row($this.parents('tr')[0]).data().roleId,
                    dataType: "json",
                    type: "DELETE",
                    success: function (data) {
                        $("#confirmDialog").modal("hide");
                        if (customGlobal.ajaxCallback(data)) {
                            dataTable.reloadTable();
                        }
                    }
                })
            })
        });
    };

    var zTreeInit = function (node) {
        node = node == undefined ? zNodes : node;
        zTreeObj = $.fn.zTree.init($("#permissionTree"), {
            check: {
                enable: true
            },
            view: {
                showLine: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        }, node);
    };

    return {
        init: function (zTreeNodes) {
            handleRecords();
            handleEvent();
            zNodes = zTreeNodes;
        }
    };
}();