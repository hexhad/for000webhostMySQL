<?php
    $serverName = "localhost";
    $databaseName = "id12536964_testinga";
    $userName = "id12536964_testing";
    $password = "passWORD@123";

    $con=mysqli_connect($serverName,$userName,$password,$databaseName);

    if(!$con){
        echo "Error occured in connection";
    }
?>