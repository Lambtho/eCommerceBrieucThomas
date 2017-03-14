<nav class="navbar navbar-default">
	<div class="container-fluid" style="width: 100%">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/client">AutoToto</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/client/afficherPanier">Panier
						( ${nbrProduitPanier} )<span class="sr-only">(current)</span>
				</a></li>


				<ul class="dropdown">
					<select id="categorie" name="idCategorie" class="dropdown">
						<c:forEach items="${listeCategories}" var="cat">
							<option value="${cat.idCategorie}">${cat.nomCategorie}</option>
						</c:forEach>
					</select>
				</ul>
			</ul>
			<form class="navbar-form navbar-left" method="get"
				action="${pageContext.request.contextPath}/client">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name="keyword" value="${keyword}">
				</div>
				<button type="submit" class="btn btn-default">Rechercher</button>
			</form>


			<ul class="nav navbar-nav navbar-right">
				<li><span>${categorie.nomCategorie}
						${categorie.description}</span></li>

				<li>Personne Connectée</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
