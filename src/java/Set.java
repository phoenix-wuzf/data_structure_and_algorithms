26   package java.util;
   27   
   28   /**
   29    * A collection that contains no duplicate elements.  More formally, sets
   30    * contain no pair of elements <code>e1</code> and <code>e2</code> such that
   31    * <code>e1.equals(e2)</code>, and at most one null element.  As implied by
   32    * its name, this interface models the mathematical <i>set</i> abstraction.
   33    *
   34    * <p>The <tt>Set</tt> interface places additional stipulations, beyond those
   35    * inherited from the <tt>Collection</tt> interface, on the contracts of all
   36    * constructors and on the contracts of the <tt>add</tt>, <tt>equals</tt> and
   37    * <tt>hashCode</tt> methods.  Declarations for other inherited methods are
   38    * also included here for convenience.  (The specifications accompanying these
   39    * declarations have been tailored to the <tt>Set</tt> interface, but they do
   40    * not contain any additional stipulations.)
   41    *
   42    * <p>The additional stipulation on constructors is, not surprisingly,
   43    * that all constructors must create a set that contains no duplicate elements
   44    * (as defined above).
   45    *
   46    * <p>Note: Great care must be exercised if mutable objects are used as set
   47    * elements.  The behavior of a set is not specified if the value of an object
   48    * is changed in a manner that affects <tt>equals</tt> comparisons while the
   49    * object is an element in the set.  A special case of this prohibition is
   50    * that it is not permissible for a set to contain itself as an element.
   51    *
   52    * <p>Some set implementations have restrictions on the elements that
   53    * they may contain.  For example, some implementations prohibit null elements,
   54    * and some have restrictions on the types of their elements.  Attempting to
   55    * add an ineligible element throws an unchecked exception, typically
   56    * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.  Attempting
   57    * to query the presence of an ineligible element may throw an exception,
   58    * or it may simply return false; some implementations will exhibit the former
   59    * behavior and some will exhibit the latter.  More generally, attempting an
   60    * operation on an ineligible element whose completion would not result in
   61    * the insertion of an ineligible element into the set may throw an
   62    * exception or it may succeed, at the option of the implementation.
   63    * Such exceptions are marked as "optional" in the specification for this
   64    * interface.
   65    *
   66    * <p>This interface is a member of the
   67    * <a href="{@docRoot}/../technotes/guides/collections/index.html">
   68    * Java Collections Framework</a>.
   69    *
   70    * @param <E> the type of elements maintained by this set
   71    *
   72    * @author  Josh Bloch
   73    * @author  Neal Gafter
   74    * @see Collection
   75    * @see List
   76    * @see SortedSet
   77    * @see HashSet
   78    * @see TreeSet
   79    * @see AbstractSet
   80    * @see Collections#singleton(java.lang.Object)
   81    * @see Collections#EMPTY_SET
   82    * @since 1.2
   83    */
   84   
   85   public interface Set<E> extends Collection<E> {
   86       // Query Operations
   87   
   88       /**
   89        * Returns the number of elements in this set (its cardinality).  If this
   90        * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
   91        * <tt>Integer.MAX_VALUE</tt>.
   92        *
   93        * @return the number of elements in this set (its cardinality)
   94        */
   95       int size();
   96   
   97       /**
   98        * Returns <tt>true</tt> if this set contains no elements.
   99        *
  100        * @return <tt>true</tt> if this set contains no elements
  101        */
  102       boolean isEmpty();
  103   
  104       /**
  105        * Returns <tt>true</tt> if this set contains the specified element.
  106        * More formally, returns <tt>true</tt> if and only if this set
  107        * contains an element <tt>e</tt> such that
  108        * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
  109        *
  110        * @param o element whose presence in this set is to be tested
  111        * @return <tt>true</tt> if this set contains the specified element
  112        * @throws ClassCastException if the type of the specified element
  113        *         is incompatible with this set
  114        * (<a href="Collection.html#optional-restrictions">optional</a>)
  115        * @throws NullPointerException if the specified element is null and this
  116        *         set does not permit null elements
  117        * (<a href="Collection.html#optional-restrictions">optional</a>)
  118        */
  119       boolean contains(Object o);
  120   
  121       /**
  122        * Returns an iterator over the elements in this set.  The elements are
  123        * returned in no particular order (unless this set is an instance of some
  124        * class that provides a guarantee).
  125        *
  126        * @return an iterator over the elements in this set
  127        */
  128       Iterator<E> iterator();
  129   
  130       /**
  131        * Returns an array containing all of the elements in this set.
  132        * If this set makes any guarantees as to what order its elements
  133        * are returned by its iterator, this method must return the
  134        * elements in the same order.
  135        *
  136        * <p>The returned array will be "safe" in that no references to it
  137        * are maintained by this set.  (In other words, this method must
  138        * allocate a new array even if this set is backed by an array).
  139        * The caller is thus free to modify the returned array.
  140        *
  141        * <p>This method acts as bridge between array-based and collection-based
  142        * APIs.
  143        *
  144        * @return an array containing all the elements in this set
  145        */
  146       Object[] toArray();
  147   
  148       /**
  149        * Returns an array containing all of the elements in this set; the
  150        * runtime type of the returned array is that of the specified array.
  151        * If the set fits in the specified array, it is returned therein.
  152        * Otherwise, a new array is allocated with the runtime type of the
  153        * specified array and the size of this set.
  154        *
  155        * <p>If this set fits in the specified array with room to spare
  156        * (i.e., the array has more elements than this set), the element in
  157        * the array immediately following the end of the set is set to
  158        * <tt>null</tt>.  (This is useful in determining the length of this
  159        * set <i>only</i> if the caller knows that this set does not contain
  160        * any null elements.)
  161        *
  162        * <p>If this set makes any guarantees as to what order its elements
  163        * are returned by its iterator, this method must return the elements
  164        * in the same order.
  165        *
  166        * <p>Like the {@link #toArray()} method, this method acts as bridge between
  167        * array-based and collection-based APIs.  Further, this method allows
  168        * precise control over the runtime type of the output array, and may,
  169        * under certain circumstances, be used to save allocation costs.
  170        *
  171        * <p>Suppose <tt>x</tt> is a set known to contain only strings.
  172        * The following code can be used to dump the set into a newly allocated
  173        * array of <tt>String</tt>:
  174        *
  175        * <pre>
  176        *     String[] y = x.toArray(new String[0]);</pre>
  177        *
  178        * Note that <tt>toArray(new Object[0])</tt> is identical in function to
  179        * <tt>toArray()</tt>.
  180        *
  181        * @param a the array into which the elements of this set are to be
  182        *        stored, if it is big enough; otherwise, a new array of the same
  183        *        runtime type is allocated for this purpose.
  184        * @return an array containing all the elements in this set
  185        * @throws ArrayStoreException if the runtime type of the specified array
  186        *         is not a supertype of the runtime type of every element in this
  187        *         set
  188        * @throws NullPointerException if the specified array is null
  189        */
  190       <T> T[] toArray(T[] a);
  191   
  192   
  193       // Modification Operations
  194   
  195       /**
  196        * Adds the specified element to this set if it is not already present
  197        * (optional operation).  More formally, adds the specified element
  198        * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
  199        * such that
  200        * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
  201        * If this set already contains the element, the call leaves the set
  202        * unchanged and returns <tt>false</tt>.  In combination with the
  203        * restriction on constructors, this ensures that sets never contain
  204        * duplicate elements.
  205        *
  206        * <p>The stipulation above does not imply that sets must accept all
  207        * elements; sets may refuse to add any particular element, including
  208        * <tt>null</tt>, and throw an exception, as described in the
  209        * specification for {@link Collection#add Collection.add}.
  210        * Individual set implementations should clearly document any
  211        * restrictions on the elements that they may contain.
  212        *
  213        * @param e element to be added to this set
  214        * @return <tt>true</tt> if this set did not already contain the specified
  215        *         element
  216        * @throws UnsupportedOperationException if the <tt>add</tt> operation
  217        *         is not supported by this set
  218        * @throws ClassCastException if the class of the specified element
  219        *         prevents it from being added to this set
  220        * @throws NullPointerException if the specified element is null and this
  221        *         set does not permit null elements
  222        * @throws IllegalArgumentException if some property of the specified element
  223        *         prevents it from being added to this set
  224        */
  225       boolean add(E e);
  226   
  227   
  228       /**
  229        * Removes the specified element from this set if it is present
  230        * (optional operation).  More formally, removes an element <tt>e</tt>
  231        * such that
  232        * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
  233        * this set contains such an element.  Returns <tt>true</tt> if this set
  234        * contained the element (or equivalently, if this set changed as a
  235        * result of the call).  (This set will not contain the element once the
  236        * call returns.)
  237        *
  238        * @param o object to be removed from this set, if present
  239        * @return <tt>true</tt> if this set contained the specified element
  240        * @throws ClassCastException if the type of the specified element
  241        *         is incompatible with this set
  242        * (<a href="Collection.html#optional-restrictions">optional</a>)
  243        * @throws NullPointerException if the specified element is null and this
  244        *         set does not permit null elements
  245        * (<a href="Collection.html#optional-restrictions">optional</a>)
  246        * @throws UnsupportedOperationException if the <tt>remove</tt> operation
  247        *         is not supported by this set
  248        */
  249       boolean remove(Object o);
  250   
  251   
  252       // Bulk Operations
  253   
  254       /**
  255        * Returns <tt>true</tt> if this set contains all of the elements of the
  256        * specified collection.  If the specified collection is also a set, this
  257        * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
  258        *
  259        * @param  c collection to be checked for containment in this set
  260        * @return <tt>true</tt> if this set contains all of the elements of the
  261        *         specified collection
  262        * @throws ClassCastException if the types of one or more elements
  263        *         in the specified collection are incompatible with this
  264        *         set
  265        * (<a href="Collection.html#optional-restrictions">optional</a>)
  266        * @throws NullPointerException if the specified collection contains one
  267        *         or more null elements and this set does not permit null
  268        *         elements
  269        * (<a href="Collection.html#optional-restrictions">optional</a>),
  270        *         or if the specified collection is null
  271        * @see    #contains(Object)
  272        */
  273       boolean containsAll(Collection<?> c);
  274   
  275       /**
  276        * Adds all of the elements in the specified collection to this set if
  277        * they're not already present (optional operation).  If the specified
  278        * collection is also a set, the <tt>addAll</tt> operation effectively
  279        * modifies this set so that its value is the <i>union</i> of the two
  280        * sets.  The behavior of this operation is undefined if the specified
  281        * collection is modified while the operation is in progress.
  282        *
  283        * @param  c collection containing elements to be added to this set
  284        * @return <tt>true</tt> if this set changed as a result of the call
  285        *
  286        * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
  287        *         is not supported by this set
  288        * @throws ClassCastException if the class of an element of the
  289        *         specified collection prevents it from being added to this set
  290        * @throws NullPointerException if the specified collection contains one
  291        *         or more null elements and this set does not permit null
  292        *         elements, or if the specified collection is null
  293        * @throws IllegalArgumentException if some property of an element of the
  294        *         specified collection prevents it from being added to this set
  295        * @see #add(Object)
  296        */
  297       boolean addAll(Collection<? extends E> c);
  298   
  299       /**
  300        * Retains only the elements in this set that are contained in the
  301        * specified collection (optional operation).  In other words, removes
  302        * from this set all of its elements that are not contained in the
  303        * specified collection.  If the specified collection is also a set, this
  304        * operation effectively modifies this set so that its value is the
  305        * <i>intersection</i> of the two sets.
  306        *
  307        * @param  c collection containing elements to be retained in this set
  308        * @return <tt>true</tt> if this set changed as a result of the call
  309        * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
  310        *         is not supported by this set
  311        * @throws ClassCastException if the class of an element of this set
  312        *         is incompatible with the specified collection
  313        * (<a href="Collection.html#optional-restrictions">optional</a>)
  314        * @throws NullPointerException if this set contains a null element and the
  315        *         specified collection does not permit null elements
  316        *         (<a href="Collection.html#optional-restrictions">optional</a>),
  317        *         or if the specified collection is null
  318        * @see #remove(Object)
  319        */
  320       boolean retainAll(Collection<?> c);
  321   
  322       /**
  323        * Removes from this set all of its elements that are contained in the
  324        * specified collection (optional operation).  If the specified
  325        * collection is also a set, this operation effectively modifies this
  326        * set so that its value is the <i>asymmetric set difference</i> of
  327        * the two sets.
  328        *
  329        * @param  c collection containing elements to be removed from this set
  330        * @return <tt>true</tt> if this set changed as a result of the call
  331        * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
  332        *         is not supported by this set
  333        * @throws ClassCastException if the class of an element of this set
  334        *         is incompatible with the specified collection
  335        * (<a href="Collection.html#optional-restrictions">optional</a>)
  336        * @throws NullPointerException if this set contains a null element and the
  337        *         specified collection does not permit null elements
  338        *         (<a href="Collection.html#optional-restrictions">optional</a>),
  339        *         or if the specified collection is null
  340        * @see #remove(Object)
  341        * @see #contains(Object)
  342        */
  343       boolean removeAll(Collection<?> c);
  344   
  345       /**
  346        * Removes all of the elements from this set (optional operation).
  347        * The set will be empty after this call returns.
  348        *
  349        * @throws UnsupportedOperationException if the <tt>clear</tt> method
  350        *         is not supported by this set
  351        */
  352       void clear();
  353   
  354   
  355       // Comparison and hashing
  356   
  357       /**
  358        * Compares the specified object with this set for equality.  Returns
  359        * <tt>true</tt> if the specified object is also a set, the two sets
  360        * have the same size, and every member of the specified set is
  361        * contained in this set (or equivalently, every member of this set is
  362        * contained in the specified set).  This definition ensures that the
  363        * equals method works properly across different implementations of the
  364        * set interface.
  365        *
  366        * @param o object to be compared for equality with this set
  367        * @return <tt>true</tt> if the specified object is equal to this set
  368        */
  369       boolean equals(Object o);
  370   
  371       /**
  372        * Returns the hash code value for this set.  The hash code of a set is
  373        * defined to be the sum of the hash codes of the elements in the set,
  374        * where the hash code of a <tt>null</tt> element is defined to be zero.
  375        * This ensures that <tt>s1.equals(s2)</tt> implies that
  376        * <tt>s1.hashCode()==s2.hashCode()</tt> for any two sets <tt>s1</tt>
  377        * and <tt>s2</tt>, as required by the general contract of
  378        * {@link Object#hashCode}.
  379        *
  380        * @return the hash code value for this set
  381        * @see Object#equals(Object)
  382        * @see Set#equals(Object)
  383        */
  384       int hashCode();
  385   }
