package controllers;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSetFormatter;
import play.*;
import play.mvc.*;

import views.html.*;

import java.io.ByteArrayOutputStream;

public class Application extends Controller {

    //public Result index() {
    //  return ok(index.render("Your new application is ready."));
    //}
    public Result index(String unsur) {
        //return ok(index.render("Your new application is ready."));

        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?a ?b ?c ?y ?x ?z WHERE {group:"+unsur+" group:atomicNumber ?y . group:"+unsur+" group:atomicWeight ?x . group:"+unsur+" group:casRegistryID ?z . group:"+unsur+" group:name ?a . group:"+unsur+" group:symbol ?b . group:"+unsur+" group:color ?c}");
        org.apache.jena.query.ResultSet results = qe.execSelect();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);
        String json = new String(outputStream.toByteArray());
        qe.close();
        return ok(json);
    }

    public Result periodiks(String unsur) {
        //return ok(index.render("Your new application is ready."));

        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?a ?b ?c ?y ?x ?z WHERE {group:"+unsur+" group:atomicNumber ?y . group:"+unsur+" group:atomicWeight ?x . group:"+unsur+" group:casRegistryID ?z . group:"+unsur+" group:name ?a . group:"+unsur+" group:symbol ?b . group:"+unsur+" group:color ?c}");
        org.apache.jena.query.ResultSet results = qe.execSelect();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);
        String json = new String(outputStream.toByteArray());
        qe.close();
        return ok(periodik.render(json));
    }

}
