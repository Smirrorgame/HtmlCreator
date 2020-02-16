
function hideNoscriptNav() {
	document.getElementById("header").style.display = "none";		//der Div container "header", der das NoScript-Menü enthält
}																	//wird beim start durch JS ausgeblendet. Wenn Js nicht verfügbar
																	//ist, bleibt es sichtbar, und das JS-Menü ist unsichtbar.

function openNav() {												//Navbar wird geöffnet
	if(window.innerWidth > 640) {
		document.getElementById("nav").style.width = "300px";			//nav wird 300px breit(geöffnet)
		document.getElementById("main").style.marginLeft = "330px";		//main bekommt 330px abstand links + 30 px abstand zu nav
		document.getElementById("footer").style.marginLeft = "330px";	//footer bekommt 330px abstand links + 30px abstand zu nav
		document.getElementById("footer").style.marginRight = "30px";	//footer bekommt 30px abstand von rechts
	} else {
		document.getElementById("nav").style.width = "90%";			//nav wird zu 90% geöffnet
	}
}


function closeNav() {												//Navbar wird geschlossen
	document.getElementById("nav").style.width = "0";				//nav wird 0px breit(geschlossen)
	document.getElementById("main").style.marginLeft = "10%";		//main bekommt 10% abstand links
	document.getElementById("footer").style.marginLeft = "0%";		//footer bekommt keinen abstand nach links
	document.getElementById("footer").style.marginRight = "0%";		//footer bekommt keinen abstand nach rechts
}

function openWohn() {												//Wohnungen-Untermenü wird geöffnet/geschlossen
	var elem = document.getElementById("menuopen");					//elem wird das Element "menuopen" zugewiesen
	//wenn menuopen geöffnet ist
	if (elem.style.display === "table") {							//Wenn menuopen als "table" angezeigt wird
		elem.style.display = "none";								//Dann soll es nicht mehr angezeigt werden
		document.getElementsByName("wohnungen")[0].classList.remove("current");	//menuopen wird die "current" Klasse entfernt (ist dann nicht mehr markiert)
	} 						
	//wenn menuopen geschlossen ist
	else {															//Wenn menuopen nicht angezeigt wird
		elem.style.display = "table";								//Dann soll menuopen als "table" angezeigt werden
		document.getElementsByName("wohnungen")[0].classList.add("current"); //menuopen bekommt die "current" Klasse (ist dann markiert)
	}
}