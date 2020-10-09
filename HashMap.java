   26   package java.util;
   27   import java.io;
   28   
   29   /**
   30    * Hash table based implementation of the <tt>Map</tt> interface.  This
   31    * implementation provides all of the optional map operations, and permits
   32    * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
   33    * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
   34    * unsynchronized and permits nulls.)  This class makes no guarantees as to
   35    * the order of the map; in particular, it does not guarantee that the order
   36    * will remain constant over time.
   37    *
   38    * <p>This implementation provides constant-time performance for the basic
   39    * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
   40    * disperses the elements properly among the buckets.  Iteration over
   41    * collection views requires time proportional to the "capacity" of the
   42    * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
   43    * of key-value mappings).  Thus, it's very important not to set the initial
   44    * capacity too high (or the load factor too low) if iteration performance is
   45    * important.
   46    *
   47    * <p>An instance of <tt>HashMap</tt> has two parameters that affect its
   48    * performance: <i>initial capacity</i> and <i>load factor</i>.  The
   49    * <i>capacity</i> is the number of buckets in the hash table, and the initial
   50    * capacity is simply the capacity at the time the hash table is created.  The
   51    * <i>load factor</i> is a measure of how full the hash table is allowed to
   52    * get before its capacity is automatically increased.  When the number of
   53    * entries in the hash table exceeds the product of the load factor and the
   54    * current capacity, the hash table is <i>rehashed</i> (that is, internal data
   55    * structures are rebuilt) so that the hash table has approximately twice the
   56    * number of buckets.
   57    *
   58    * <p>As a general rule, the default load factor (.75) offers a good tradeoff
   59    * between time and space costs.  Higher values decrease the space overhead
   60    * but increase the lookup cost (reflected in most of the operations of the
   61    * <tt>HashMap</tt> class, including <tt>get</tt> and <tt>put</tt>).  The
   62    * expected number of entries in the map and its load factor should be taken
   63    * into account when setting its initial capacity, so as to minimize the
   64    * number of rehash operations.  If the initial capacity is greater
   65    * than the maximum number of entries divided by the load factor, no
   66    * rehash operations will ever occur.
   67    *
   68    * <p>If many mappings are to be stored in a <tt>HashMap</tt> instance,
   69    * creating it with a sufficiently large capacity will allow the mappings to
   70    * be stored more efficiently than letting it perform automatic rehashing as
   71    * needed to grow the table.
   72    *
   73    * <p><strong>Note that this implementation is not synchronized.</strong>
   74    * If multiple threads access a hash map concurrently, and at least one of
   75    * the threads modifies the map structurally, it <i>must</i> be
   76    * synchronized externally.  (A structural modification is any operation
   77    * that adds or deletes one or more mappings; merely changing the value
   78    * associated with a key that an instance already contains is not a
   79    * structural modification.)  This is typically accomplished by
   80    * synchronizing on some object that naturally encapsulates the map.
   81    *
   82    * If no such object exists, the map should be "wrapped" using the
   83    * {@link Collections#synchronizedMap Collections.synchronizedMap}
   84    * method.  This is best done at creation time, to prevent accidental
   85    * unsynchronized access to the map:<pre>
   86    *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
   87    *
   88    * <p>The iterators returned by all of this class's "collection view methods"
   89    * are <i>fail-fast</i>: if the map is structurally modified at any time after
   90    * the iterator is created, in any way except through the iterator's own
   91    * <tt>remove</tt> method, the iterator will throw a
   92    * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
   93    * modification, the iterator fails quickly and cleanly, rather than risking
   94    * arbitrary, non-deterministic behavior at an undetermined time in the
   95    * future.
   96    *
   97    * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
   98    * as it is, generally speaking, impossible to make any hard guarantees in the
   99    * presence of unsynchronized concurrent modification.  Fail-fast iterators
  100    * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
  101    * Therefore, it would be wrong to write a program that depended on this
  102    * exception for its correctness: <i>the fail-fast behavior of iterators
  103    * should be used only to detect bugs.</i>
  104    *
  105    * <p>This class is a member of the
  106    * <a href="{@docRoot}/../technotes/guides/collections/index.html">
  107    * Java Collections Framework</a>.
  108    *
  109    * @param <K> the type of keys maintained by this map
  110    * @param <V> the type of mapped values
  111    *
  112    * @author  Doug Lea
  113    * @author  Josh Bloch
  114    * @author  Arthur van Hoff
  115    * @author  Neal Gafter
  116    * @see     Object#hashCode()
  117    * @see     Collection
  118    * @see     Map
  119    * @see     TreeMap
  120    * @see     Hashtable
  121    * @since   1.2
  122    */
  123   
  124   public class HashMap<K,V>
  125       extends AbstractMap<K,V>
  126       implements Map<K,V>, Cloneable, Serializable
  127   {
  128   
  129       /**
  130        * The default initial capacity - MUST be a power of two.
  131        */
  132       static final int DEFAULT_INITIAL_CAPACITY = 16;
  133   
  134       /**
  135        * The maximum capacity, used if a higher value is implicitly specified
  136        * by either of the constructors with arguments.
  137        * MUST be a power of two <= 1<<30.
  138        */
  139       static final int MAXIMUM_CAPACITY = 1 << 30;
  140   
  141       /**
  142        * The load factor used when none specified in constructor.
  143        */
  144       static final float DEFAULT_LOAD_FACTOR = 0.75f;
  145   
  146       /**
  147        * The table, resized as necessary. Length MUST Always be a power of two.
  148        */
  149       transient Entry[] table;
  150   
  151       /**
  152        * The number of key-value mappings contained in this map.
  153        */
  154       transient int size;
  155   
  156       /**
  157        * The next size value at which to resize (capacity * load factor).
  158        * @serial
  159        */
  160       int threshold;
  161   
  162       /**
  163        * The load factor for the hash table.
  164        *
  165        * @serial
  166        */
  167       final float loadFactor;
  168   
  169       /**
  170        * The number of times this HashMap has been structurally modified
  171        * Structural modifications are those that change the number of mappings in
  172        * the HashMap or otherwise modify its internal structure (e.g.,
  173        * rehash).  This field is used to make iterators on Collection-views of
  174        * the HashMap fail-fast.  (See ConcurrentModificationException).
  175        */
  176       transient int modCount;
  177   
  178       /**
  179        * Constructs an empty <tt>HashMap</tt> with the specified initial
  180        * capacity and load factor.
  181        *
  182        * @param  initialCapacity the initial capacity
  183        * @param  loadFactor      the load factor
  184        * @throws IllegalArgumentException if the initial capacity is negative
  185        *         or the load factor is nonpositive
  186        */
  187       public HashMap(int initialCapacity, float loadFactor) {
  188           if (initialCapacity < 0)
  189               throw new IllegalArgumentException("Illegal initial capacity: " +
  190                                                  initialCapacity);
  191           if (initialCapacity > MAXIMUM_CAPACITY)
  192               initialCapacity = MAXIMUM_CAPACITY;
  193           if (loadFactor <= 0 || Float.isNaN(loadFactor))
  194               throw new IllegalArgumentException("Illegal load factor: " +
  195                                                  loadFactor);
  196   
  197           // Find a power of 2 >= initialCapacity
  198           int capacity = 1;
  199           while (capacity < initialCapacity)
  200               capacity <<= 1;
  201   
  202           this.loadFactor = loadFactor;
  203           threshold = (int)(capacity * loadFactor);
  204           table = new Entry[capacity];
  205           init();
  206       }
  207   
  208       /**
  209        * Constructs an empty <tt>HashMap</tt> with the specified initial
  210        * capacity and the default load factor (0.75).
  211        *
  212        * @param  initialCapacity the initial capacity.
  213        * @throws IllegalArgumentException if the initial capacity is negative.
  214        */
  215       public HashMap(int initialCapacity) {
  216           this(initialCapacity, DEFAULT_LOAD_FACTOR);
  217       }
  218   
  219       /**
  220        * Constructs an empty <tt>HashMap</tt> with the default initial capacity
  221        * (16) and the default load factor (0.75).
  222        */
  223       public HashMap() {
  224           this.loadFactor = DEFAULT_LOAD_FACTOR;
  225           threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
  226           table = new Entry[DEFAULT_INITIAL_CAPACITY];
  227           init();
  228       }
  229   
  230       /**
  231        * Constructs a new <tt>HashMap</tt> with the same mappings as the
  232        * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
  233        * default load factor (0.75) and an initial capacity sufficient to
  234        * hold the mappings in the specified <tt>Map</tt>.
  235        *
  236        * @param   m the map whose mappings are to be placed in this map
  237        * @throws  NullPointerException if the specified map is null
  238        */
  239       public HashMap(Map<? extends K, ? extends V> m) {
  240           this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
  241                         DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
  242           putAllForCreate(m);
  243       }
  244   
  245       // internal utilities
  246   
  247       /**
  248        * Initialization hook for subclasses. This method is called
  249        * in all constructors and pseudo-constructors (clone, readObject)
  250        * after HashMap has been initialized but before any entries have
  251        * been inserted.  (In the absence of this method, readObject would
  252        * require explicit knowledge of subclasses.)
  253        */
  254       void init() {
  255       }
  256   
  257       /**
  258        * Applies a supplemental hash function to a given hashCode, which
  259        * defends against poor quality hash functions.  This is critical
  260        * because HashMap uses power-of-two length hash tables, that
  261        * otherwise encounter collisions for hashCodes that do not differ
  262        * in lower bits. Note: Null keys always map to hash 0, thus index 0.
  263        */
  264       static int hash(int h) {
  265           // This function ensures that hashCodes that differ only by
  266           // constant multiples at each bit position have a bounded
  267           // number of collisions (approximately 8 at default load factor).
  268           h ^= (h >>> 20) ^ (h >>> 12);
  269           return h ^ (h >>> 7) ^ (h >>> 4);
  270       }
  271   
  272       /**
  273        * Returns index for hash code h.
  274        */
  275       static int indexFor(int h, int length) {
  276           return h & (length-1);
  277       }
  278   
  279       /**
  280        * Returns the number of key-value mappings in this map.
  281        *
  282        * @return the number of key-value mappings in this map
  283        */
  284       public int size() {
  285           return size;
  286       }
  287   
  288       /**
  289        * Returns <tt>true</tt> if this map contains no key-value mappings.
  290        *
  291        * @return <tt>true</tt> if this map contains no key-value mappings
  292        */
  293       public boolean isEmpty() {
  294           return size == 0;
  295       }
  296   
  297       /**
  298        * Returns the value to which the specified key is mapped,
  299        * or {@code null} if this map contains no mapping for the key.
  300        *
  301        * <p>More formally, if this map contains a mapping from a key
  302        * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
  303        * key.equals(k))}, then this method returns {@code v}; otherwise
  304        * it returns {@code null}.  (There can be at most one such mapping.)
  305        *
  306        * <p>A return value of {@code null} does not <i>necessarily</i>
  307        * indicate that the map contains no mapping for the key; it's also
  308        * possible that the map explicitly maps the key to {@code null}.
  309        * The {@link #containsKey containsKey} operation may be used to
  310        * distinguish these two cases.
  311        *
  312        * @see #put(Object, Object)
  313        */
  314       public V get(Object key) {
  315           if (key == null)
  316               return getForNullKey();
  317           int hash = hash(key.hashCode());
  318           for (Entry<K,V> e = table[indexFor(hash, table.length)];
  319                e != null;
  320                e = e.next) {
  321               Object k;
  322               if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
  323                   return e.value;
  324           }
  325           return null;
  326       }
  327   
  328       /**
  329        * Offloaded version of get() to look up null keys.  Null keys map
  330        * to index 0.  This null case is split out into separate methods
  331        * for the sake of performance in the two most commonly used
  332        * operations (get and put), but incorporated with conditionals in
  333        * others.
  334        */
  335       private V getForNullKey() {
  336           for (Entry<K,V> e = table[0]; e != null; e = e.next) {
  337               if (e.key == null)
  338                   return e.value;
  339           }
  340           return null;
  341       }
  342   
  343       /**
  344        * Returns <tt>true</tt> if this map contains a mapping for the
  345        * specified key.
  346        *
  347        * @param   key   The key whose presence in this map is to be tested
  348        * @return <tt>true</tt> if this map contains a mapping for the specified
  349        * key.
  350        */
  351       public boolean containsKey(Object key) {
  352           return getEntry(key) != null;
  353       }
  354   
  355       /**
  356        * Returns the entry associated with the specified key in the
  357        * HashMap.  Returns null if the HashMap contains no mapping
  358        * for the key.
  359        */
  360       final Entry<K,V> getEntry(Object key) {
  361           int hash = (key == null) ? 0 : hash(key.hashCode());
  362           for (Entry<K,V> e = table[indexFor(hash, table.length)];
  363                e != null;
  364                e = e.next) {
  365               Object k;
  366               if (e.hash == hash &&
  367                   ((k = e.key) == key || (key != null && key.equals(k))))
  368                   return e;
  369           }
  370           return null;
  371       }
  372   
  373   
  374       /**
  375        * Associates the specified value with the specified key in this map.
  376        * If the map previously contained a mapping for the key, the old
  377        * value is replaced.
  378        *
  379        * @param key key with which the specified value is to be associated
  380        * @param value value to be associated with the specified key
  381        * @return the previous value associated with <tt>key</tt>, or
  382        *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
  383        *         (A <tt>null</tt> return can also indicate that the map
  384        *         previously associated <tt>null</tt> with <tt>key</tt>.)
  385        */
  386       public V put(K key, V value) {
  387           if (key == null)
  388               return putForNullKey(value);
  389           int hash = hash(key.hashCode());
  390           int i = indexFor(hash, table.length);
  391           for (Entry<K,V> e = table[i]; e != null; e = e.next) {
  392               Object k;
  393               if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
  394                   V oldValue = e.value;
  395                   e.value = value;
  396                   e.recordAccess(this);
  397                   return oldValue;
  398               }
  399           }
  400   
  401           modCount++;
  402           addEntry(hash, key, value, i);
  403           return null;
  404       }
  405   
  406       /**
  407        * Offloaded version of put for null keys
  408        */
  409       private V putForNullKey(V value) {
  410           for (Entry<K,V> e = table[0]; e != null; e = e.next) {
  411               if (e.key == null) {
  412                   V oldValue = e.value;
  413                   e.value = value;
  414                   e.recordAccess(this);
  415                   return oldValue;
  416               }
  417           }
  418           modCount++;
  419           addEntry(0, null, value, 0);
  420           return null;
  421       }
  422   
  423       /**
  424        * This method is used instead of put by constructors and
  425        * pseudoconstructors (clone, readObject).  It does not resize the table,
  426        * check for comodification, etc.  It calls createEntry rather than
  427        * addEntry.
  428        */
  429       private void putForCreate(K key, V value) {
  430           int hash = (key == null) ? 0 : hash(key.hashCode());
  431           int i = indexFor(hash, table.length);
  432   
  433           /**
  434            * Look for preexisting entry for key.  This will never happen for
  435            * clone or deserialize.  It will only happen for construction if the
  436            * input Map is a sorted map whose ordering is inconsistent w/ equals.
  437            */
  438           for (Entry<K,V> e = table[i]; e != null; e = e.next) {
  439               Object k;
  440               if (e.hash == hash &&
  441                   ((k = e.key) == key || (key != null && key.equals(k)))) {
  442                   e.value = value;
  443                   return;
  444               }
  445           }
  446   
  447           createEntry(hash, key, value, i);
  448       }
  449   
  450       private void putAllForCreate(Map<? extends K, ? extends V> m) {
  451           for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
  452               putForCreate(e.getKey(), e.getValue());
  453       }
  454   
  455       /**
  456        * Rehashes the contents of this map into a new array with a
  457        * larger capacity.  This method is called automatically when the
  458        * number of keys in this map reaches its threshold.
  459        *
  460        * If current capacity is MAXIMUM_CAPACITY, this method does not
  461        * resize the map, but sets threshold to Integer.MAX_VALUE.
  462        * This has the effect of preventing future calls.
  463        *
  464        * @param newCapacity the new capacity, MUST be a power of two;
  465        *        must be greater than current capacity unless current
  466        *        capacity is MAXIMUM_CAPACITY (in which case value
  467        *        is irrelevant).
  468        */
  469       void resize(int newCapacity) {
  470           Entry[] oldTable = table;
  471           int oldCapacity = oldTable.length;
  472           if (oldCapacity == MAXIMUM_CAPACITY) {
  473               threshold = Integer.MAX_VALUE;
  474               return;
  475           }
  476   
  477           Entry[] newTable = new Entry[newCapacity];
  478           transfer(newTable);
  479           table = newTable;
  480           threshold = (int)(newCapacity * loadFactor);
  481       }
  482   
  483       /**
  484        * Transfers all entries from current table to newTable.
  485        */
  486       void transfer(Entry[] newTable) {
  487           Entry[] src = table;
  488           int newCapacity = newTable.length;
  489           for (int j = 0; j < src.length; j++) {
  490               Entry<K,V> e = src[j];
  491               if (e != null) {
  492                   src[j] = null;
  493                   do {
  494                       Entry<K,V> next = e.next;
  495                       int i = indexFor(e.hash, newCapacity);
  496                       e.next = newTable[i];
  497                       newTable[i] = e;
  498                       e = next;
  499                   } while (e != null);
  500               }
  501           }
  502       }
  503   
  504       /**
  505        * Copies all of the mappings from the specified map to this map.
  506        * These mappings will replace any mappings that this map had for
  507        * any of the keys currently in the specified map.
  508        *
  509        * @param m mappings to be stored in this map
  510        * @throws NullPointerException if the specified map is null
  511        */
  512       public void putAll(Map<? extends K, ? extends V> m) {
  513           int numKeysToBeAdded = m.size();
  514           if (numKeysToBeAdded == 0)
  515               return;
  516   
  517           /*
  518            * Expand the map if the map if the number of mappings to be added
  519            * is greater than or equal to threshold.  This is conservative; the
  520            * obvious condition is (m.size() + size) >= threshold, but this
  521            * condition could result in a map with twice the appropriate capacity,
  522            * if the keys to be added overlap with the keys already in this map.
  523            * By using the conservative calculation, we subject ourself
  524            * to at most one extra resize.
  525            */
  526           if (numKeysToBeAdded > threshold) {
  527               int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
  528               if (targetCapacity > MAXIMUM_CAPACITY)
  529                   targetCapacity = MAXIMUM_CAPACITY;
  530               int newCapacity = table.length;
  531               while (newCapacity < targetCapacity)
  532                   newCapacity <<= 1;
  533               if (newCapacity > table.length)
  534                   resize(newCapacity);
  535           }
  536   
  537           for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
  538               put(e.getKey(), e.getValue());
  539       }
  540   
  541       /**
  542        * Removes the mapping for the specified key from this map if present.
  543        *
  544        * @param  key key whose mapping is to be removed from the map
  545        * @return the previous value associated with <tt>key</tt>, or
  546        *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
  547        *         (A <tt>null</tt> return can also indicate that the map
  548        *         previously associated <tt>null</tt> with <tt>key</tt>.)
  549        */
  550       public V remove(Object key) {
  551           Entry<K,V> e = removeEntryForKey(key);
  552           return (e == null ? null : e.value);
  553       }
  554   
  555       /**
  556        * Removes and returns the entry associated with the specified key
  557        * in the HashMap.  Returns null if the HashMap contains no mapping
  558        * for this key.
  559        */
  560       final Entry<K,V> removeEntryForKey(Object key) {
  561           int hash = (key == null) ? 0 : hash(key.hashCode());
  562           int i = indexFor(hash, table.length);
  563           Entry<K,V> prev = table[i];
  564           Entry<K,V> e = prev;
  565   
  566           while (e != null) {
  567               Entry<K,V> next = e.next;
  568               Object k;
  569               if (e.hash == hash &&
  570                   ((k = e.key) == key || (key != null && key.equals(k)))) {
  571                   modCount++;
  572                   size--;
  573                   if (prev == e)
  574                       table[i] = next;
  575                   else
  576                       prev.next = next;
  577                   e.recordRemoval(this);
  578                   return e;
  579               }
  580               prev = e;
  581               e = next;
  582           }
  583   
  584           return e;
  585       }
  586   
  587       /**
  588        * Special version of remove for EntrySet.
  589        */
  590       final Entry<K,V> removeMapping(Object o) {
  591           if (!(o instanceof Map.Entry))
  592               return null;
  593   
  594           Map.Entry<K,V> entry = (Map.Entry<K,V>) o;
  595           Object key = entry.getKey();
  596           int hash = (key == null) ? 0 : hash(key.hashCode());
  597           int i = indexFor(hash, table.length);
  598           Entry<K,V> prev = table[i];
  599           Entry<K,V> e = prev;
  600   
  601           while (e != null) {
  602               Entry<K,V> next = e.next;
  603               if (e.hash == hash && e.equals(entry)) {
  604                   modCount++;
  605                   size--;
  606                   if (prev == e)
  607                       table[i] = next;
  608                   else
  609                       prev.next = next;
  610                   e.recordRemoval(this);
  611                   return e;
  612               }
  613               prev = e;
  614               e = next;
  615           }
  616   
  617           return e;
  618       }
  619   
  620       /**
  621        * Removes all of the mappings from this map.
  622        * The map will be empty after this call returns.
  623        */
  624       public void clear() {
  625           modCount++;
  626           Entry[] tab = table;
  627           for (int i = 0; i < tab.length; i++)
  628               tab[i] = null;
  629           size = 0;
  630       }
  631   
  632       /**
  633        * Returns <tt>true</tt> if this map maps one or more keys to the
  634        * specified value.
  635        *
  636        * @param value value whose presence in this map is to be tested
  637        * @return <tt>true</tt> if this map maps one or more keys to the
  638        *         specified value
  639        */
  640       public boolean containsValue(Object value) {
  641           if (value == null)
  642               return containsNullValue();
  643   
  644           Entry[] tab = table;
  645           for (int i = 0; i < tab.length ; i++)
  646               for (Entry e = tab[i] ; e != null ; e = e.next)
  647                   if (value.equals(e.value))
  648                       return true;
  649           return false;
  650       }
  651   
  652       /**
  653        * Special-case code for containsValue with null argument
  654        */
  655       private boolean containsNullValue() {
  656           Entry[] tab = table;
  657           for (int i = 0; i < tab.length ; i++)
  658               for (Entry e = tab[i] ; e != null ; e = e.next)
  659                   if (e.value == null)
  660                       return true;
  661           return false;
  662       }
  663   
  664       /**
  665        * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
  666        * values themselves are not cloned.
  667        *
  668        * @return a shallow copy of this map
  669        */
  670       public Object clone() {
  671           HashMap<K,V> result = null;
  672           try {
  673               result = (HashMap<K,V>)super.clone();
  674           } catch (CloneNotSupportedException e) {
  675               // assert false;
  676           }
  677           result.table = new Entry[table.length];
  678           result.entrySet = null;
  679           result.modCount = 0;
  680           result.size = 0;
  681           result.init();
  682           result.putAllForCreate(this);
  683   
  684           return result;
  685       }
  686   
  687       static class Entry<K,V> implements Map.Entry<K,V> {
  688           final K key;
  689           V value;
  690           Entry<K,V> next;
  691           final int hash;
  692   
  693           /**
  694            * Creates new entry.
  695            */
  696           Entry(int h, K k, V v, Entry<K,V> n) {
  697               value = v;
  698               next = n;
  699               key = k;
  700               hash = h;
  701           }
  702   
  703           public final K getKey() {
  704               return key;
  705           }
  706   
  707           public final V getValue() {
  708               return value;
  709           }
  710   
  711           public final V setValue(V newValue) {
  712               V oldValue = value;
  713               value = newValue;
  714               return oldValue;
  715           }
  716   
  717           public final boolean equals(Object o) {
  718               if (!(o instanceof Map.Entry))
  719                   return false;
  720               Map.Entry e = (Map.Entry)o;
  721               Object k1 = getKey();
  722               Object k2 = e.getKey();
  723               if (k1 == k2 || (k1 != null && k1.equals(k2))) {
  724                   Object v1 = getValue();
  725                   Object v2 = e.getValue();
  726                   if (v1 == v2 || (v1 != null && v1.equals(v2)))
  727                       return true;
  728               }
  729               return false;
  730           }
  731   
  732           public final int hashCode() {
  733               return (key==null   ? 0 : key.hashCode()) ^
  734                      (value==null ? 0 : value.hashCode());
  735           }
  736   
  737           public final String toString() {
  738               return getKey() + "=" + getValue();
  739           }
  740   
  741           /**
  742            * This method is invoked whenever the value in an entry is
  743            * overwritten by an invocation of put(k,v) for a key k that's already
  744            * in the HashMap.
  745            */
  746           void recordAccess(HashMap<K,V> m) {
  747           }
  748   
  749           /**
  750            * This method is invoked whenever the entry is
  751            * removed from the table.
  752            */
  753           void recordRemoval(HashMap<K,V> m) {
  754           }
  755       }
  756   
  757       /**
  758        * Adds a new entry with the specified key, value and hash code to
  759        * the specified bucket.  It is the responsibility of this
  760        * method to resize the table if appropriate.
  761        *
  762        * Subclass overrides this to alter the behavior of put method.
  763        */
  764       void addEntry(int hash, K key, V value, int bucketIndex) {
  765           Entry<K,V> e = table[bucketIndex];
  766           table[bucketIndex] = new Entry<>(hash, key, value, e);
  767           if (size++ >= threshold)
  768               resize(2 * table.length);
  769       }
  770   
  771       /**
  772        * Like addEntry except that this version is used when creating entries
  773        * as part of Map construction or "pseudo-construction" (cloning,
  774        * deserialization).  This version needn't worry about resizing the table.
  775        *
  776        * Subclass overrides this to alter the behavior of HashMap(Map),
  777        * clone, and readObject.
  778        */
  779       void createEntry(int hash, K key, V value, int bucketIndex) {
  780           Entry<K,V> e = table[bucketIndex];
  781           table[bucketIndex] = new Entry<>(hash, key, value, e);
  782           size++;
  783       }
  784   
  785       private abstract class HashIterator<E> implements Iterator<E> {
  786           Entry<K,V> next;        // next entry to return
  787           int expectedModCount;   // For fast-fail
  788           int index;              // current slot
  789           Entry<K,V> current;     // current entry
  790   
  791           HashIterator() {
  792               expectedModCount = modCount;
  793               if (size > 0) { // advance to first entry
  794                   Entry[] t = table;
  795                   while (index < t.length && (next = t[index++]) == null)
  796                       ;
  797               }
  798           }
  799   
  800           public final boolean hasNext() {
  801               return next != null;
  802           }
  803   
  804           final Entry<K,V> nextEntry() {
  805               if (modCount != expectedModCount)
  806                   throw new ConcurrentModificationException();
  807               Entry<K,V> e = next;
  808               if (e == null)
  809                   throw new NoSuchElementException();
  810   
  811               if ((next = e.next) == null) {
  812                   Entry[] t = table;
  813                   while (index < t.length && (next = t[index++]) == null)
  814                       ;
  815               }
  816               current = e;
  817               return e;
  818           }
  819   
  820           public void remove() {
  821               if (current == null)
  822                   throw new IllegalStateException();
  823               if (modCount != expectedModCount)
  824                   throw new ConcurrentModificationException();
  825               Object k = current.key;
  826               current = null;
  827               HashMap.this.removeEntryForKey(k);
  828               expectedModCount = modCount;
  829           }
  830   
  831       }
  832   
  833       private final class ValueIterator extends HashIterator<V> {
  834           public V next() {
  835               return nextEntry().value;
  836           }
  837       }
  838   
  839       private final class KeyIterator extends HashIterator<K> {
  840           public K next() {
  841               return nextEntry().getKey();
  842           }
  843       }
  844   
  845       private final class EntryIterator extends HashIterator<Map.Entry<K,V>> {
  846           public Map.Entry<K,V> next() {
  847               return nextEntry();
  848           }
  849       }
  850   
  851       // Subclass overrides these to alter behavior of views' iterator() method
  852       Iterator<K> newKeyIterator()   {
  853           return new KeyIterator();
  854       }
  855       Iterator<V> newValueIterator()   {
  856           return new ValueIterator();
  857       }
  858       Iterator<Map.Entry<K,V>> newEntryIterator()   {
  859           return new EntryIterator();
  860       }
  861   
  862   
  863       // Views
  864   
  865       private transient Set<Map.Entry<K,V>> entrySet = null;
  866   
  867       /**
  868        * Returns a {@link Set} view of the keys contained in this map.
  869        * The set is backed by the map, so changes to the map are
  870        * reflected in the set, and vice-versa.  If the map is modified
  871        * while an iteration over the set is in progress (except through
  872        * the iterator's own <tt>remove</tt> operation), the results of
  873        * the iteration are undefined.  The set supports element removal,
  874        * which removes the corresponding mapping from the map, via the
  875        * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
  876        * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
  877        * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
  878        * operations.
  879        */
  880       public Set<K> keySet() {
  881           Set<K> ks = keySet;
  882           return (ks != null ? ks : (keySet = new KeySet()));
  883       }
  884   
  885       private final class KeySet extends AbstractSet<K> {
  886           public Iterator<K> iterator() {
  887               return newKeyIterator();
  888           }
  889           public int size() {
  890               return size;
  891           }
  892           public boolean contains(Object o) {
  893               return containsKey(o);
  894           }
  895           public boolean remove(Object o) {
  896               return HashMap.this.removeEntryForKey(o) != null;
  897           }
  898           public void clear() {
  899               HashMap.this.clear();
  900           }
  901       }
  902   
  903       /**
  904        * Returns a {@link Collection} view of the values contained in this map.
  905        * The collection is backed by the map, so changes to the map are
  906        * reflected in the collection, and vice-versa.  If the map is
  907        * modified while an iteration over the collection is in progress
  908        * (except through the iterator's own <tt>remove</tt> operation),
  909        * the results of the iteration are undefined.  The collection
  910        * supports element removal, which removes the corresponding
  911        * mapping from the map, via the <tt>Iterator.remove</tt>,
  912        * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
  913        * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
  914        * support the <tt>add</tt> or <tt>addAll</tt> operations.
  915        */
  916       public Collection<V> values() {
  917           Collection<V> vs = values;
  918           return (vs != null ? vs : (values = new Values()));
  919       }
  920   
  921       private final class Values extends AbstractCollection<V> {
  922           public Iterator<V> iterator() {
  923               return newValueIterator();
  924           }
  925           public int size() {
  926               return size;
  927           }
  928           public boolean contains(Object o) {
  929               return containsValue(o);
  930           }
  931           public void clear() {
  932               HashMap.this.clear();
  933           }
  934       }
  935   
  936       /**
  937        * Returns a {@link Set} view of the mappings contained in this map.
  938        * The set is backed by the map, so changes to the map are
  939        * reflected in the set, and vice-versa.  If the map is modified
  940        * while an iteration over the set is in progress (except through
  941        * the iterator's own <tt>remove</tt> operation, or through the
  942        * <tt>setValue</tt> operation on a map entry returned by the
  943        * iterator) the results of the iteration are undefined.  The set
  944        * supports element removal, which removes the corresponding
  945        * mapping from the map, via the <tt>Iterator.remove</tt>,
  946        * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
  947        * <tt>clear</tt> operations.  It does not support the
  948        * <tt>add</tt> or <tt>addAll</tt> operations.
  949        *
  950        * @return a set view of the mappings contained in this map
  951        */
  952       public Set<Map.Entry<K,V>> entrySet() {
  953           return entrySet0();
  954       }
  955   
  956       private Set<Map.Entry<K,V>> entrySet0() {
  957           Set<Map.Entry<K,V>> es = entrySet;
  958           return es != null ? es : (entrySet = new EntrySet());
  959       }
  960   
  961       private final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
  962           public Iterator<Map.Entry<K,V>> iterator() {
  963               return newEntryIterator();
  964           }
  965           public boolean contains(Object o) {
  966               if (!(o instanceof Map.Entry))
  967                   return false;
  968               Map.Entry<K,V> e = (Map.Entry<K,V>) o;
  969               Entry<K,V> candidate = getEntry(e.getKey());
  970               return candidate != null && candidate.equals(e);
  971           }
  972           public boolean remove(Object o) {
  973               return removeMapping(o) != null;
  974           }
  975           public int size() {
  976               return size;
  977           }
  978           public void clear() {
  979               HashMap.this.clear();
  980           }
  981       }
  982   
  983       /**
  984        * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
  985        * serialize it).
  986        *
  987        * @serialData The <i>capacity</i> of the HashMap (the length of the
  988        *             bucket array) is emitted (int), followed by the
  989        *             <i>size</i> (an int, the number of key-value
  990        *             mappings), followed by the key (Object) and value (Object)
  991        *             for each key-value mapping.  The key-value mappings are
  992        *             emitted in no particular order.
  993        */
  994       private void writeObject(java.io.ObjectOutputStream s)
  995           throws IOException
  996       {
  997           Iterator<Map.Entry<K,V>> i =
  998               (size > 0) ? entrySet0().iterator() : null;
  999   
 1000           // Write out the threshold, loadfactor, and any hidden stuff
 1001           s.defaultWriteObject();
 1002   
 1003           // Write out number of buckets
 1004           s.writeInt(table.length);
 1005   
 1006           // Write out size (number of Mappings)
 1007           s.writeInt(size);
 1008   
 1009           // Write out keys and values (alternating)
 1010           if (i != null) {
 1011               while (i.hasNext()) {
 1012                   Map.Entry<K,V> e = i.next();
 1013                   s.writeObject(e.getKey());
 1014                   s.writeObject(e.getValue());
 1015               }
 1016           }
 1017       }
 1018   
 1019       private static final long serialVersionUID = 362498820763181265L;
 1020   
 1021       /**
 1022        * Reconstitute the <tt>HashMap</tt> instance from a stream (i.e.,
 1023        * deserialize it).
 1024        */
 1025       private void readObject(java.io.ObjectInputStream s)
 1026            throws IOException, ClassNotFoundException
 1027       {
 1028           // Read in the threshold, loadfactor, and any hidden stuff
 1029           s.defaultReadObject();
 1030   
 1031           // Read in number of buckets and allocate the bucket array;
 1032           int numBuckets = s.readInt();
 1033           table = new Entry[numBuckets];
 1034   
 1035           init();  // Give subclass a chance to do its thing.
 1036   
 1037           // Read in size (number of Mappings)
 1038           int size = s.readInt();
 1039   
 1040           // Read the keys and values, and put the mappings in the HashMap
 1041           for (int i=0; i<size; i++) {
 1042               K key = (K) s.readObject();
 1043               V value = (V) s.readObject();
 1044               putForCreate(key, value);
 1045           }
 1046       }
 1047   
 1048       // These methods are used when serializing HashSets
 1049       int   capacity()     { return table.length; }
 1050       float loadFactor()   { return loadFactor;   }
 1051   }
