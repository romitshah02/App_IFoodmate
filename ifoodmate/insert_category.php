<?php

require_once "connection.php";

$category_id =validate($_POST['CATEGORY_ID']);
$category_name =validate($_POST['CATEGORY_NAME']);
$category_status =validate($_POST['CATEGORY_STATUS']);

$sql = "INSERT INTO `mst_category`(`CATEGORY_ID`, `CATEGORY_NAME`, `CATEGORY_STATUS`) VALUES ('$category_id','$category_name','$category_status')";
if ($conn->query($sql))
{
    echo "Category entered successfully";
}
else
{
    echo "Error in category " . $sql . "" . mysqli_error($conn);
}

$conn->close();

?>