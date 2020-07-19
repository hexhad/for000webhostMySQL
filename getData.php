<?php
    include_once "connection.php";

    class user{}

    $username=$_POST['username'];
    $email=$_POST['email'];

    $query=mysqli_query($con,"INSERT INTO user(username,email) values ('$username','$email')");
    if($query){
        $response=new user();
        $response->sucess=1;
        $response->message="sucessfully uploaded";
        die(json_encode($response));

    }else{

        $response=new user();
        $response->sucess=0;
        $response->message="Error occured";
        die(json_encode($response));
    }

    mysqli_close($con);
?>