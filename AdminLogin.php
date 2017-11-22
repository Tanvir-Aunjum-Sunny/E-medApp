<?php
    $con = mysqli_connect("mysql12.000webhost.com", "a4554073_emed", "emeddb1", "a4554073_eMedDB");
    
    $admin_name = $_POST["admin_name"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM adminInfo WHERE admin_name = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $admin_name, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $username, $age, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        //$response["success"] = true;  
        //$response["name"] = $name;
        //$response["age"] = $age;
        $response["admin_name"] = $admin_name;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>