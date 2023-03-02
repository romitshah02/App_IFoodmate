<?php

require_once "connection.php";
require_once "validate.php";

if (!empty($_POST['CAT_IMG']))
{
    $path = date("d-m-Y").'-'.time().rand(10000,100000000).'.jpg';
    if (file_put_contents($path,
    base64_decode($_POST['CAT_IMG'])))
    {
        $category_name = validate($_POST['CATEGORY_NAME']);
        $category_status = 1;
        $query = "INSERT INTO `mst_category`(`CATEGORY_NAME`,`CATEGORY_STATUS`,`CAT_IMG`) VALUES ('$category_name','$category_status','".$path."')";
        if ($conn->query($query))
        {
            echo "success";
        }
        else
        {
            echo "failure";
        }
    }
    else
    {
        echo "failure";
    }


}


$conn->close();

?>