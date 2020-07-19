<?php
    define('DB_HOST','localhost');
    define('DB_USER','id12536964_testing');
    define('DB_PASS','passWORD@123');
    define('DB_NAME', 'id12536964_testinga');

$conn=new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
if(mysqli_connect_errno()){
    echo "Failed to connect";
    die();
}
$qry=$conn->prepare("select * from user");
$qry->execute();
$qry->bind_result($username,$email);
$data=array();

while($qry->fetch()){
    $temp=array();
    $temp['username']=$username;
    $temp['email']=$email;
    array_push($data,$temp);
}

echo json_encode($data);
?>