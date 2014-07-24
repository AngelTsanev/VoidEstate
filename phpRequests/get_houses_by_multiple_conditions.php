<?php

function create_query($price_low, $price_high, $size, $nearest_city, $radius, $bedrooms) {
  $options = array(
    'price_low' => $price_low,
    'price_high' => $price_high,
    'size' => $size,
    //'nearest_city' => $nearest_city,
    //'radius' => $radius,
    'bedrooms' => $bedrooms
    );

  $cond = '';
  $noopt = true;
  $conditions = false;
  foreach ($options as $column => $value) {
    if ($value != -1) {
        $noopt = false;
        $conditions = true;
        if ($cond != '') $cond .= ' AND ';

        if ($column == 'price_low'){
            $cond .= "price > $value";
        }elseif ($column == 'price_high'){
            $cond .= "price < $value";
        }elseif ($column == 'size'){
            $cond .= "$column = $value";
        }elseif ($column == 'bedrooms'){
            $cond .= "$column = $value";
        }
      }
    }
    if (($nearest_city != -1) and ($radius != -1) and $conditions){
        $noopt = false;
        $cond .= " AND nearest_city LIKE '%$nearest_city%' AND distance_to_near_city < $radius";
    }elseif (($nearest_city != -1) and ($radius != -1)){
        $noopt = false;
        $cond .= " nearest_city LIKE '%$nearest_city%' AND distance_to_near_city < $radius";
    }



$host="localhost"; //replace with database hostname
$username="root"; //replace with database username
$password="swordfish"; //replace with database password
$db_name="voidestate"; //replace with database name

//if those vars remain -1, they are unseted
$price_low  = -1;
$price_high = -1;
$size  = -1;
$nearest_city  = -1;
$radius     = -1;
$bedrooms   = -1;

$connection = mysql_connect("$host", "$username", "$password")or die("cannot connect");
mysql_select_db("$db_name")or die("cannot select DB");

if (isset($_GET["price_low"])) $price_low = $_GET['price_low'];

if (isset($_GET["price_high"])) $price_high = $_GET['price_high'];

if (isset($_GET["nearest_city"])) $nearest_city = $_GET['nearest_city'];

if (isset($_GET["radius"])) $radius = $_GET['radius'];

if (isset($_GET["size"])) $size = $_GET['size'];

if (isset($_GET["bedrooms"])) $bedrooms = $_GET['bedrooms'];


    $sql = create_query($price_low, $price_high, $size, $nearest_city, $radius, $bedrooms);
    $result = mysql_query($sql);
    $json = array();
    if(mysql_num_rows($result)){
        while($row=mysql_fetch_assoc($result)){
            $json['houses'][]=$row;
        }
    }
    mysql_close($connection);


    echo json_encode($json);

 ?>
