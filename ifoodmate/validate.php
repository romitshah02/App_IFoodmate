<?php
function validate($data)
{
    $data = trim($data);
    $dta = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
?>