<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<label for="nomCompteUtilisateur">Nom <span class="requis">*</span></label>
<input type="text" id="nomCompteUtilisateur" name="nomCompteUtilisateur" value="<c:out value="${cu.nom}"/>" size="20" maxlength="20"/>
<span class="erreur">${form.erreurs['nomClient']}</span>
<br/>

<label for="prenomCompteUtilisateur">Prénom <span class="requis">*</span></label>
<input type="text" id="prenomCompteUtilisateur" name="prenomCompteUtilisateur" value="<c:out value="${cu.prenom}"/>" size="20" maxlength="20"/>
<span class="erreur">${form.erreurs['prenomClient']}</span>
<br/>
			
<label for="pseudoCompteUtilisateur">Pseudo <span class="requis">*</span></label>
<input type="text" id="pseudoCompteUtilisateur" name="pseudoCompteUtilisateur" value="<c:out value="${cu.pseudo}"/>" size="20" maxlength="20"/>
<span class="erreur">${form.erreurs['adresseClient']}</span>
<br/>

<label for="emailCompteUtilisateur">Adresse email</label>
<input type="email" id="emailCompteUtilisateur" name="emailCompteUtilisateur" value="<c:out value="${cu.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['emailClient']}</span>
<br />
		
<label for="mdpCompteUtilisateur">Mot de passe <span class="requis">*</span></label>
<input type="password" id="mdpCompteUtilisateur" name="mdpCompteUtilisateur" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['telephoneClient']}</span>
<br />

<label for="mdpCompteUtilisateurConf">Confirmation du mot de passe <span class="requis">*</span></label>
<input type="password" id="mdpCompteUtilisateurConf" name="mdpCompteUtilisateurConf" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['telephoneClient']}</span>
<br />

<br />
