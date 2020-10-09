 165: public interface Deque<E> extends Queue<E> {
 166:     /**
 167:      * Inserts the specified element at the front of this deque if it is
 168:      * possible to do so immediately without violating capacity restrictions.
 169:      * When using a capacity-restricted deque, it is generally preferable to
 170:      * use method {@link #offerFirst}.
 171:      *
 172:      * @param e the element to add
 173:      * @throws IllegalStateException if the element cannot be added at this
 174:      *         time due to capacity restrictions
 175:      * @throws ClassCastException if the class of the specified element
 176:      *         prevents it from being added to this deque
 177:      * @throws NullPointerException if the specified element is null and this
 178:      *         deque does not permit null elements
 179:      * @throws IllegalArgumentException if some property of the specified
 180:      *         element prevents it from being added to this deque
 181:      */
 182:     void addFirst(E e);
 183: 
 184:     /**
 185:      * Inserts the specified element at the end of this deque if it is
 186:      * possible to do so immediately without violating capacity restrictions.
 187:      * When using a capacity-restricted deque, it is generally preferable to
 188:      * use method {@link #offerLast}.
 189:      *
 190:      * <p>This method is equivalent to {@link #add}.
 191:      *
 192:      * @param e the element to add
 193:      * @throws IllegalStateException if the element cannot be added at this
 194:      *         time due to capacity restrictions
 195:      * @throws ClassCastException if the class of the specified element
 196:      *         prevents it from being added to this deque
 197:      * @throws NullPointerException if the specified element is null and this
 198:      *         deque does not permit null elements
 199:      * @throws IllegalArgumentException if some property of the specified
 200:      *         element prevents it from being added to this deque
 201:      */
 202:     void addLast(E e);
 203: 
 204:     /**
 205:      * Inserts the specified element at the front of this deque unless it would
 206:      * violate capacity restrictions.  When using a capacity-restricted deque,
 207:      * this method is generally preferable to the {@link #addFirst} method,
 208:      * which can fail to insert an element only by throwing an exception.
 209:      *
 210:      * @param e the element to add
 211:      * @return <tt>true</tt> if the element was added to this deque, else
 212:      *         <tt>false</tt>
 213:      * @throws ClassCastException if the class of the specified element
 214:      *         prevents it from being added to this deque
 215:      * @throws NullPointerException if the specified element is null and this
 216:      *         deque does not permit null elements
 217:      * @throws IllegalArgumentException if some property of the specified
 218:      *         element prevents it from being added to this deque
 219:      */
 220:     boolean offerFirst(E e);
 221: 
 222:     /**
 223:      * Inserts the specified element at the end of this deque unless it would
 224:      * violate capacity restrictions.  When using a capacity-restricted deque,
 225:      * this method is generally preferable to the {@link #addLast} method,
 226:      * which can fail to insert an element only by throwing an exception.
 227:      *
 228:      * @param e the element to add
 229:      * @return <tt>true</tt> if the element was added to this deque, else
 230:      *         <tt>false</tt>
 231:      * @throws ClassCastException if the class of the specified element
 232:      *         prevents it from being added to this deque
 233:      * @throws NullPointerException if the specified element is null and this
 234:      *         deque does not permit null elements
 235:      * @throws IllegalArgumentException if some property of the specified
 236:      *         element prevents it from being added to this deque
 237:      */
 238:     boolean offerLast(E e);
 239: 
 240:     /**
 241:      * Retrieves and removes the first element of this deque.  This method
 242:      * differs from {@link #pollFirst pollFirst} only in that it throws an
 243:      * exception if this deque is empty.
 244:      *
 245:      * @return the head of this deque
 246:      * @throws NoSuchElementException if this deque is empty
 247:      */
 248:     E removeFirst();
 249: 
 250:     /**
 251:      * Retrieves and removes the last element of this deque.  This method
 252:      * differs from {@link #pollLast pollLast} only in that it throws an
 253:      * exception if this deque is empty.
 254:      *
 255:      * @return the tail of this deque
 256:      * @throws NoSuchElementException if this deque is empty
 257:      */
 258:     E removeLast();
 259: 
 260:     /**
 261:      * Retrieves and removes the first element of this deque,
 262:      * or returns <tt>null</tt> if this deque is empty.
 263:      *
 264:      * @return the head of this deque, or <tt>null</tt> if this deque is empty
 265:      */
 266:     E pollFirst();
 267: 
 268:     /**
 269:      * Retrieves and removes the last element of this deque,
 270:      * or returns <tt>null</tt> if this deque is empty.
 271:      *
 272:      * @return the tail of this deque, or <tt>null</tt> if this deque is empty
 273:      */
 274:     E pollLast();
 275: 
 276:     /**
 277:      * Retrieves, but does not remove, the first element of this deque.
 278:      *
 279:      * This method differs from {@link #peekFirst peekFirst} only in that it
 280:      * throws an exception if this deque is empty.
 281:      *
 282:      * @return the head of this deque
 283:      * @throws NoSuchElementException if this deque is empty
 284:      */
 285:     E getFirst();
 286: 
 287:     /**
 288:      * Retrieves, but does not remove, the last element of this deque.
 289:      * This method differs from {@link #peekLast peekLast} only in that it
 290:      * throws an exception if this deque is empty.
 291:      *
 292:      * @return the tail of this deque
 293:      * @throws NoSuchElementException if this deque is empty
 294:      */
 295:     E getLast();
 296: 
 297:     /**
 298:      * Retrieves, but does not remove, the first element of this deque,
 299:      * or returns <tt>null</tt> if this deque is empty.
 300:      *
 301:      * @return the head of this deque, or <tt>null</tt> if this deque is empty
 302:      */
 303:     E peekFirst();
 304: 
 305:     /**
 306:      * Retrieves, but does not remove, the last element of this deque,
 307:      * or returns <tt>null</tt> if this deque is empty.
 308:      *
 309:      * @return the tail of this deque, or <tt>null</tt> if this deque is empty
 310:      */
 311:     E peekLast();
 312: 
 313:     /**
 314:      * Removes the first occurrence of the specified element from this deque.
 315:      * If the deque does not contain the element, it is unchanged.
 316:      * More formally, removes the first element <tt>e</tt> such that
 317:      * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>
 318:      * (if such an element exists).
 319:      * Returns <tt>true</tt> if this deque contained the specified element
 320:      * (or equivalently, if this deque changed as a result of the call).
 321:      *
 322:      * @param o element to be removed from this deque, if present
 323:      * @return <tt>true</tt> if an element was removed as a result of this call
 324:      * @throws ClassCastException if the class of the specified element
 325:      *         is incompatible with this deque (optional)
 326:      * @throws NullPointerException if the specified element is null and this
 327:      *         deque does not permit null elements (optional)
 328:      */
 329:     boolean removeFirstOccurrence(Object o);
 330: 
 331:     /**
 332:      * Removes the last occurrence of the specified element from this deque.
 333:      * If the deque does not contain the element, it is unchanged.
 334:      * More formally, removes the last element <tt>e</tt> such that
 335:      * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>
 336:      * (if such an element exists).
 337:      * Returns <tt>true</tt> if this deque contained the specified element
 338:      * (or equivalently, if this deque changed as a result of the call).
 339:      *
 340:      * @param o element to be removed from this deque, if present
 341:      * @return <tt>true</tt> if an element was removed as a result of this call
 342:      * @throws ClassCastException if the class of the specified element
 343:      *         is incompatible with this deque (optional)
 344:      * @throws NullPointerException if the specified element is null and this
 345:      *         deque does not permit null elements (optional)
 346:      */
 347:     boolean removeLastOccurrence(Object o);
 348: 
 349:     // *** Queue methods ***
 350: 
 351:     /**
 352:      * Inserts the specified element into the queue represented by this deque
 353:      * (in other words, at the tail of this deque) if it is possible to do so
 354:      * immediately without violating capacity restrictions, returning
 355:      * <tt>true</tt> upon success and throwing an
 356:      * <tt>IllegalStateException</tt> if no space is currently available.
 357:      * When using a capacity-restricted deque, it is generally preferable to
 358:      * use {@link #offer(Object) offer}.
 359:      *
 360:      * <p>This method is equivalent to {@link #addLast}.
 361:      *
 362:      * @param e the element to add
 363:      * @return <tt>true</tt> (as specified by {@link Collection#add})
 364:      * @throws IllegalStateException if the element cannot be added at this
 365:      *         time due to capacity restrictions
 366:      * @throws ClassCastException if the class of the specified element
 367:      *         prevents it from being added to this deque
 368:      * @throws NullPointerException if the specified element is null and this
 369:      *         deque does not permit null elements
 370:      * @throws IllegalArgumentException if some property of the specified
 371:      *         element prevents it from being added to this deque
 372:      */
 373:     boolean add(E e);
 374: 
 375:     /**
 376:      * Inserts the specified element into the queue represented by this deque
 377:      * (in other words, at the tail of this deque) if it is possible to do so
 378:      * immediately without violating capacity restrictions, returning
 379:      * <tt>true</tt> upon success and <tt>false</tt> if no space is currently
 380:      * available.  When using a capacity-restricted deque, this method is
 381:      * generally preferable to the {@link #add} method, which can fail to
 382:      * insert an element only by throwing an exception.
 383:      *
 384:      * <p>This method is equivalent to {@link #offerLast}.
 385:      *
 386:      * @param e the element to add
 387:      * @return <tt>true</tt> if the element was added to this deque, else
 388:      *         <tt>false</tt>
 389:      * @throws ClassCastException if the class of the specified element
 390:      *         prevents it from being added to this deque
 391:      * @throws NullPointerException if the specified element is null and this
 392:      *         deque does not permit null elements
 393:      * @throws IllegalArgumentException if some property of the specified
 394:      *         element prevents it from being added to this deque
 395:      */
 396:     boolean offer(E e);
 397: 
 398:     /**
 399:      * Retrieves and removes the head of the queue represented by this deque
 400:      * (in other words, the first element of this deque).
 401:      * This method differs from {@link #poll poll} only in that it throws an
 402:      * exception if this deque is empty.
 403:      *
 404:      * <p>This method is equivalent to {@link #removeFirst()}.
 405:      *
 406:      * @return the head of the queue represented by this deque
 407:      * @throws NoSuchElementException if this deque is empty
 408:      */
 409:     E remove();
 410: 
 411:     /**
 412:      * Retrieves and removes the head of the queue represented by this deque
 413:      * (in other words, the first element of this deque), or returns
 414:      * <tt>null</tt> if this deque is empty.
 415:      *
 416:      * <p>This method is equivalent to {@link #pollFirst()}.
 417:      *
 418:      * @return the first element of this deque, or <tt>null</tt> if
 419:      *         this deque is empty
 420:      */
 421:     E poll();
 422: 
 423:     /**
 424:      * Retrieves, but does not remove, the head of the queue represented by
 425:      * this deque (in other words, the first element of this deque).
 426:      * This method differs from {@link #peek peek} only in that it throws an
 427:      * exception if this deque is empty.
 428:      *
 429:      * <p>This method is equivalent to {@link #getFirst()}.
 430:      *
 431:      * @return the head of the queue represented by this deque
 432:      * @throws NoSuchElementException if this deque is empty
 433:      */
 434:     E element();
 435: 
 436:     /**
 437:      * Retrieves, but does not remove, the head of the queue represented by
 438:      * this deque (in other words, the first element of this deque), or
 439:      * returns <tt>null</tt> if this deque is empty.
 440:      *
 441:      * <p>This method is equivalent to {@link #peekFirst()}.
 442:      *
 443:      * @return the head of the queue represented by this deque, or
 444:      *         <tt>null</tt> if this deque is empty
 445:      */
 446:     E peek();
 447: 
 448: 
 449:     // *** Stack methods ***
 450: 
 451:     /**
 452:      * Pushes an element onto the stack represented by this deque (in other
 453:      * words, at the head of this deque) if it is possible to do so
 454:      * immediately without violating capacity restrictions, returning
 455:      * <tt>true</tt> upon success and throwing an
 456:      * <tt>IllegalStateException</tt> if no space is currently available.
 457:      *
 458:      * <p>This method is equivalent to {@link #addFirst}.
 459:      *
 460:      * @param e the element to push
 461:      * @throws IllegalStateException if the element cannot be added at this
 462:      *         time due to capacity restrictions
 463:      * @throws ClassCastException if the class of the specified element
 464:      *         prevents it from being added to this deque
 465:      * @throws NullPointerException if the specified element is null and this
 466:      *         deque does not permit null elements
 467:      * @throws IllegalArgumentException if some property of the specified
 468:      *         element prevents it from being added to this deque
 469:      */
 470:     void push(E e);
 471: 
 472:     /**
 473:      * Pops an element from the stack represented by this deque.  In other
 474:      * words, removes and returns the first element of this deque.
 475:      *
 476:      * <p>This method is equivalent to {@link #removeFirst()}.
 477:      *
 478:      * @return the element at the front of this deque (which is the top
 479:      *         of the stack represented by this deque)
 480:      * @throws NoSuchElementException if this deque is empty
 481:      */
 482:     E pop();
 483: 
 484: 
 485:     // *** Collection methods ***
 486: 
 487:     /**
 488:      * Removes the first occurrence of the specified element from this deque.
 489:      * If the deque does not contain the element, it is unchanged.
 490:      * More formally, removes the first element <tt>e</tt> such that
 491:      * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>
 492:      * (if such an element exists).
 493:      * Returns <tt>true</tt> if this deque contained the specified element
 494:      * (or equivalently, if this deque changed as a result of the call).
 495:      *
 496:      * <p>This method is equivalent to {@link #removeFirstOccurrence}.
 497:      *
 498:      * @param o element to be removed from this deque, if present
 499:      * @return <tt>true</tt> if an element was removed as a result of this call
 500:      * @throws ClassCastException if the class of the specified element
 501:      *         is incompatible with this deque (optional)
 502:      * @throws NullPointerException if the specified element is null and this
 503:      *         deque does not permit null elements (optional)
 504:      */
 505:     boolean remove(Object o);
 506: 
 507:     /**
 508:      * Returns <tt>true</tt> if this deque contains the specified element.
 509:      * More formally, returns <tt>true</tt> if and only if this deque contains
 510:      * at least one element <tt>e</tt> such that
 511:      * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
 512:      *
 513:      * @param o element whose presence in this deque is to be tested
 514:      * @return <tt>true</tt> if this deque contains the specified element
 515:      * @throws ClassCastException if the type of the specified element
 516:      *         is incompatible with this deque (optional)
 517:      * @throws NullPointerException if the specified element is null and this
 518:      *         deque does not permit null elements (optional)
 519:      */
 520:     boolean contains(Object o);
 521: 
 522:     /**
 523:      * Returns the number of elements in this deque.
 524:      *
 525:      * @return the number of elements in this deque
 526:      */
 527:     public int size();
 528: 
 529:     /**
 530:      * Returns an iterator over the elements in this deque in proper sequence.
 531:      * The elements will be returned in order from first (head) to last (tail).
 532:      *
 533:      * @return an iterator over the elements in this deque in proper sequence
 534:      */
 535:     Iterator<E> iterator();
 536: 
 537:     /**
 538:      * Returns an iterator over the elements in this deque in reverse
 539:      * sequential order.  The elements will be returned in order from
 540:      * last (tail) to first (head).
 541:      *
 542:      * @return an iterator over the elements in this deque in reverse
 543:      * sequence
 544:      */
 545:     Iterator<E> descendingIterator();
 546: 
 547: }
