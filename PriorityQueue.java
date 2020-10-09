  39: package java.util;
  40: 
  41: import java.io.Serializable;
  42: 
  43: /**
  44:  * @author Tom Tromey (tromey@redhat.com)
  45:  * @author Andrew John Hughes (gnu_andrew@member.fsf.org)
  46:  * @since 1.5
  47:  */
  48: public class PriorityQueue<E> extends AbstractQueue<E> implements Serializable
  49: {
  50:   private static final int DEFAULT_CAPACITY = 11;
  51: 
  52:   private static final long serialVersionUID = -7720805057305804111L;
  53: 
  54:   /** Number of elements actually used in the storage array.  */
  55:   int used;
  56: 
  57:   /**
  58:    * This is the storage for the underlying binomial heap.
  59:    * The idea is, each node is less than or equal to its children.
  60:    * A node at index N (0-based) has two direct children, at
  61:    * nodes 2N+1 and 2N+2.
  62:    */
  63:   E[] storage;
  64: 
  65:   /**
  66:    * The comparator we're using, or null for natural ordering.
  67:    */
  68:   Comparator<? super E> comparator;
  69: 
  70:   public PriorityQueue()
  71:   {
  72:     this(DEFAULT_CAPACITY, null);
  73:   }
  74: 
  75:   public PriorityQueue(Collection<? extends E> c)
  76:   {
  77:     this(Math.max(1, (int) (1.1 * c.size())), null);
  78: 
  79:     // Special case where we can find the comparator to use.
  80:     if (c instanceof SortedSet)
  81:       {
  82:         SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
  83:         this.comparator = (Comparator<? super E>) ss.comparator();
  84:         // We can insert the elements directly, since they are sorted.
  85:         int i = 0;
  86:         for (E val : ss)
  87:           {
  88:             if (val == null)
  89:               throw new NullPointerException();
  90:             storage[i++] = val;
  91:           }
  92:       }
  93:     else if (c instanceof PriorityQueue)
  94:       {
  95:         PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
  96:         this.comparator = (Comparator<? super E>)pq.comparator();
  97:         // We can just copy the contents.
  98:         System.arraycopy(pq.storage, 0, storage, 0, pq.storage.length);
  99:       }
 100: 
 101:     addAll(c);
 102:   }
 103: 
 104:   public PriorityQueue(int cap)
 105:   {
 106:     this(cap, null);
 107:   }
 108: 
 109:   public PriorityQueue(int cap, Comparator<? super E> comp)
 110:   {
 111:     if (cap < 1)
 112:       throw new IllegalArgumentException();      
 113:     this.used = 0;
 114:     this.storage = (E[]) new Object[cap];
 115:     this.comparator = comp;
 116:   }
 117: 
 118:   public PriorityQueue(PriorityQueue<? extends E> c)
 119:   {
 120:     this(Math.max(1, (int) (1.1 * c.size())),
 121:          (Comparator<? super E>)c.comparator());
 122:     // We can just copy the contents.
 123:     System.arraycopy(c.storage, 0, storage, 0, c.storage.length);
 124:   }
 125: 
 126:   public PriorityQueue(SortedSet<? extends E> c)
 127:   {
 128:     this(Math.max(1, (int) (1.1 * c.size())),
 129:          (Comparator<? super E>)c.comparator());
 130:     // We can insert the elements directly, since they are sorted.
 131:     int i = 0;
 132:     for (E val : c)
 133:       {
 134:         if (val == null)
 135:           throw new NullPointerException();
 136:         storage[i++] = val;
 137:       }
 138:   }
 139: 
 140:   public void clear()
 141:   {
 142:     Arrays.fill(storage, null);
 143:     used = 0;
 144:   }
 145: 
 146:   public Comparator<? super E> comparator()
 147:   {
 148:     return comparator;
 149:   }
 150: 
 151:   public Iterator<E> iterator()
 152:   {
 153:     return new Iterator<E>()
 154:     {
 155:       int index = -1;
 156:       int count = 0;
 157: 
 158:       public boolean hasNext()
 159:       {
 160:         return count < used;
 161:       }
 162: 
 163:       public E next()
 164:       {
 165:         while (storage[++index] == null)
 166:           ;
 167: 
 168:         ++count;
 169:         return storage[index];
 170:       }
 171: 
 172:       public void remove()
 173:       {
 174:         PriorityQueue.this.remove(index);
 175:     index--;
 176:       }
 177:     };
 178:   }
 179: 
 180:   public boolean offer(E o)
 181:   {
 182:     if (o == null)
 183:       throw new NullPointerException();
 184: 
 185:     int slot = findSlot(-1);
 186: 
 187:     storage[slot] = o;
 188:     ++used;
 189:     bubbleUp(slot);
 190: 
 191:     return true;
 192:   }
 193: 
 194:   public E peek()
 195:   {
 196:     return used == 0 ? null : storage[0];
 197:   }
 198: 
 199:   public E poll()
 200:   {
 201:     if (used == 0)
 202:       return null;
 203:     E result = storage[0];
 204:     remove(0);
 205:     return result;
 206:   }
 207: 
 208:   public boolean remove(Object o)
 209:   {
 210:     if (o != null)
 211:       {
 212:         for (int i = 0; i < storage.length; ++i)
 213:           {
 214:             if (o.equals(storage[i]))
 215:               {
 216:                 remove(i);
 217:                 return true;
 218:               }
 219:           }
 220:       }
 221:     return false;
 222:   }
 223: 
 224:   public int size()
 225:   {
 226:     return used;
 227:   }
 228: 
 229:   // It is more efficient to implement this locally -- less searching
 230:   // for free slots.
 231:   public boolean addAll(Collection<? extends E> c)
 232:   {
 233:     if (c == this)
 234:       throw new IllegalArgumentException();
 235: 
 236:     int newSlot = -1;
 237:     int save = used;
 238:     for (E val : c)
 239:       {
 240:         if (val == null)
 241:           throw new NullPointerException();
 242:         newSlot = findSlot(newSlot);
 243:         storage[newSlot] = val;
 244:         ++used;
 245:         bubbleUp(newSlot);
 246:       }
 247: 
 248:     return save != used;
 249:   }
 250: 
 251:   int findSlot(int start)
 252:   {
 253:     int slot;
 254:     if (used == storage.length)
 255:       {
 256:         resize();
 257:         slot = used;
 258:       }
 259:     else
 260:       {
 261:         for (slot = start + 1; slot < storage.length; ++slot)
 262:           {
 263:             if (storage[slot] == null)
 264:               break;
 265:           }
 266:         // We'll always find a slot.
 267:       }
 268:     return slot;
 269:   }
 270: 
 271:   void remove(int index)
 272:   {
 273:     // Remove the element at INDEX.  We do this by finding the least
 274:     // child and moving it into place, then iterating until we reach
 275:     // the bottom of the tree.
 276:     while (storage[index] != null)
 277:       {
 278:         int child = 2 * index + 1;
 279: 
 280:         // See if we went off the end.
 281:         if (child >= storage.length)
 282:           {
 283:             storage[index] = null;
 284:             break;
 285:           }
 286: 
 287:         // Find which child we want to promote.  If one is not null,
 288:         // we pick it.  If both are null, it doesn't matter, we're
 289:         // about to leave.  If neither is null, pick the lesser.
 290:         if (child + 1 >= storage.length || storage[child + 1] == null)
 291:           {
 292:             // Nothing.
 293:           }
 294:         else if (storage[child] == null
 295:                  || (Collections.compare(storage[child], storage[child + 1],
 296:                                          comparator) > 0))
 297:           ++child;
 298:         storage[index] = storage[child];
 299:         index = child;
 300:       }
 301:     --used;
 302:   }
 303: 
 304:   void bubbleUp(int index)
 305:   {
 306:     // The element at INDEX was inserted into a blank spot.  Now move
 307:     // it up the tree to its natural resting place.
 308:     while (index > 0)
 309:       {
 310:         // This works regardless of whether we're at 2N+1 or 2N+2.
 311:         int parent = (index - 1) / 2;
 312:         if (Collections.compare(storage[parent], storage[index], comparator)
 313:             <= 0)
 314:           {
 315:             // Parent is the same or smaller than this element, so the
 316:             // invariant is preserved.  Note that if the new element
 317:             // is smaller than the parent, then it is necessarily
 318:             // smaller than the parent's other child.
 319:             break;
 320:           }
 321: 
 322:         E temp = storage[index];
 323:         storage[index] = storage[parent];
 324:         storage[parent] = temp;
 325: 
 326:         index = parent;
 327:       }
 328:   }
 329: 
 330:   void resize()
 331:   {
 332:     E[] new_data = (E[]) new Object[2 * storage.length];
 333:     System.arraycopy(storage, 0, new_data, 0, storage.length);
 334:     storage = new_data;
 335:   }
 336: }
