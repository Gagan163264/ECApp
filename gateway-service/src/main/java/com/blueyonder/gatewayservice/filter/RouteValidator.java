package com.blueyonder.gatewayservice.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Predicate;
@Component
public class RouteValidator {

	public static  final List<String> openApiEndpoints=List.of(
			"/auth/register",
			"/auth/login",
			"/eureka",
			"/api-docs"
	);
	public static  final List<String> isAdmin=List.of(
			"/ecapp/v1/category/addcategory",
			"/ecapp/v1/category/update",
			"/ecapp/v1/category/delete",
			"/ecapp/v1/category/unlinkproduct",

			"/ecapp/v1/product/addproduct",
			"/ecapp/v1/product/update",
			"/ecapp/v1/product/delete",
			"/ecapp/v1/product/linkproductTocategory",
			"/ecapp/v1/product/unlinkproductFromcategory",

			"/auth/registerAdmin",
			"/auth/getAllusers"
	);

	public Predicate<ServerHttpRequest> isSecured =
			request -> openApiEndpoints
					.stream()
					.noneMatch(uri -> request.getURI().getPath().contains(uri));
	public Predicate<ServerHttpRequest> isAdminSecured =
			request -> isAdmin
					.stream()
					.anyMatch(uri -> request.getURI().getPath().contains(uri));

}
