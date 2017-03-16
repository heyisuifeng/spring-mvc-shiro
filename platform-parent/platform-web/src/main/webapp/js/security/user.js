var userListTable = function () {

    var dataTable;
    var $table = $("#dataTable");
    /**
     * dataTable事件初始化方法
     */
    var handleRecords = function () {
        dataTable = new Datatable();
        dataTable.init({
            src: $table,
            onQuery: function (data) {
                data.username = $("#usernameQuery").val().trim();
                data.nickname = $("#nicknameQuery").val().trim();
            },
            dataTable: {
                "ajax": {
                    "url": basePath + "security/user/getUserListPage"
                },
                "columns": [
                    {data: 'username', orderable: true, searchable: true},
                    {data: 'nickname', orderable: true, searchable: true},
                    {data: 'operate', orderable: false,
                        render: function (data, type, full) {
                            return '<a class="edit btn default btn-xs purple" userId="' + full.userId + '"><i class="fa fa-edit"></i>'+i18n['edit']+'</a>&nbsp;'
                                + '<a class="delete btn default btn-xs black" data-target="#confirmDialog" data-toggle="modal"><i class="fa fa-times"></i>'+i18n["delete"]+'</a>&nbsp;';
                        }
                    }
                ]
            }
        });
    };

    var handleEvent = function () {
        $("#addUser").on("click", function () {
            $("#modalTitle").html(i18n["addUser"]);
            customGlobal.clearFormAndShowDialog("modalDialog");
            $('#roleList').select2();
            $("#dialogForm").validate({
                rules: {
                    username: {
                        required: true
                    },
                    nickname: {
                        required: true
                    },
                    rePassword: {
                        equalTo: "#password"
                    }
                }
            }).resetForm();
            $("#password").rules("add", "required");
            $("#rePassword").rules("add", "required");
            $("#passwordHelpBlock").html("");
            $("#addBtn").show();
            $("#updateBtn").hide();
        });


        $("#addBtn").on("click", function () {
            if ($("#dialogForm").validate().form()) {
                customGlobal.blockUI("#modalContent");
                $.ajax({
                    url: "security/user",
                    data: JSON.stringify({
                        username: $("#username").val(),
                        nickname: $("#nickname").val(),
                        password: $("#password").val().md5(),
                        roleList: $("#roleList").val()
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

            $("#modalTitle").html(i18n['editUser']);
            customGlobal.clearFormAndShowDialog("modalDialog");
            $("#dialogForm").validate({
                rules: {
                    username: {
                        required: true
                    },
                    nickname: {
                        required: true
                    },
                    rePassword: {
                        equalTo: "#password"
                    }
                }
            }).resetForm();
            $("#password").rules("remove", "required");
            $("#rePassword").rules("remove", "required");
            $("#addBtn").hide();
            $("#updateBtn").show();
            $.get("security/user/" + $(this).attr("userId"), function (data) {
                var user = data.returnData.user;
                $("#userId").val(user.userId);
                $("#username").val(user.username);
                $("#nickname").val(user.nickname);
                $("#passwordHelpBlock").html(i18n["noFillNoChangePWD"]);
                var $roleList = $("#roleList");
                $roleList.find("option").removeAttr("selected");
                var roleList = data.returnData.roleList;
                for (var i = 0; i < roleList.length; i++) {
                    var str = "option[value=" + roleList[i].roleId + "]";
                    $roleList.find(str).attr("selected", "selected")
                }
                $roleList.select2();
            });
        });

        $("#updateBtn").on("click", function () {
            if ($("#dialogForm").validate().form()) {
                customGlobal.blockUI("#modalContent");
                var data = {
                    userId: $("#userId").val(),
                    username: $("#username").val(),
                    nickname: $("#nickname").val(),
                    roleList: $("#roleList").val()
                };
                var $password = $("#password");
                if ($password.val() != "") {
                    data.password = $password.val().md5();
                }
                $.ajax({
                    url: "security/user",
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
                    url: "security/user/" + $table.DataTable().row($this.parents('tr')[0]).data().userId,
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


    return {
        init: function () {
            handleRecords();
            handleEvent();
        }
    };
}();