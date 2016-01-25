<?php 
	
	if ((count($_GET)!=0) && !(isset($_GET['controle']) && isset ($_GET['action'])))
	require ('./vue/erreur404.tpl'); //cas d'un appel à index.php avec des paramètres incorrects
	else {
		if ((isset($_SESSION['profil'])) || count($_GET)==0)	{
			$controle = "jeu";   //cas d'une personne non authentifiée
			$action=	"accueil";		//ou d'un appel à index.php sans paramètre
		}
		else {
			
			if (isset($_GET['controle']) && isset ($_GET['action'])) {
				$controle = $_GET['controle'];   //cas d'un appel à index.php 
				$action = 	 $_GET['action'];	//avec les 2 paramètres controle et action
			}
		}
		require ('./controle/' . $controle . '.php');
		$action ();
	} 
	
?>
