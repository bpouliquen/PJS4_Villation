<?php

// chez vous, par exemple

$hote='localhost';   		
$login='root';  	
$pass='';
$bd='villation';  //le nom de la base, en fait

$link =null;


$link = mysqli_connect($hote, $login, $pass) 
		or die ("erreur de connexion :" . mysqli_connect_error() . 'numéro :' . mysqli_connect_errno()); 
mysqli_select_db($link, $bd) 
		or die ("erreur d'accès à la base :" . $bd);
		
//test : die ('ok connexion'); 

?>