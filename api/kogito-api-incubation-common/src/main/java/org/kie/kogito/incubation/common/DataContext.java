package org.kie.kogito.incubation.common;

/**
 * A type that represents a computational context.
 *
 * <ul>
 * <li>A DataContext is a computational context. It contains all the data that
 * is required by a service to perform its computation.</li>
 * <li>A DataContext can be always converted into another type
 * (or another DataContext) using the method {@link DataContext#as(Class)}</li>
 * <li></li>A DataContext can be always converted into another sub-type of DataContext.</li>
 * <li>Converting a DataContext into the same DataContext is a no-op just like (Object) new Object() is a no-op.
 * <li>You are always able to convert a DataContext into an arbitrary sub-type; e.g. a {@link MapDataContext}
 * into a class that implements DataContext.</li>
 * </ul>
 * <p>
 * Example:
 *
 * <code><pre>
 * class Person implements DataContext, DefaultCastable { String name; }
 * MapDataContext ctx = MapDataContext.create();
 * ctx.set("name", "Paul");
 * Person p = ctx.as(Person.class);
 * String name = p.name; // "Paul"
 * </pre></code>
 * <p>
 * {@see MapDataContext}
 */
public interface DataContext extends Castable, ReferenceContext {
}
