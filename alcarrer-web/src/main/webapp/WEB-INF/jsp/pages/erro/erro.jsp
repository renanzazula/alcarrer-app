<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fieldset>
	<legend>Error</legend>
	<ul class="form-style-1">
		<li>
			<table>
				<tr>
					<td>Error</td>
					<td>${error}</td>
				</tr>
			</table>
		</li>
	</ul>
</fieldset>

