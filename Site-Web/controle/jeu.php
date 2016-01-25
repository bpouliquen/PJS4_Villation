<?php

require("./vue/header.tpl");
require("./vue/navigation.tpl");


function accueil(){
	echo "Accueil (présentation du jeu, screen...)";
}

function statistiques() {
	echo "Stats";
}

function download() {
	include("./vue/jeu/telechargement.tpl");
}

function about(){
	echo "A propos (description de la team)";
}


?>