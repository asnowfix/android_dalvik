/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dalvik.system;

/**
 * Provides a limited interface to the Dalvik VM stack. This class is mostly
 * used for implementing security checks.
 *
 * @deprecated this is an internal Dalvik class that is not appropriate for
 *      general use. It will be removed from the public API in a future release.
 */
public final class VMStack {
    /**
     * Returns the defining class loader of the caller's caller.
     * 
     * @return the requested class loader, or {@code null} if this is the
     *         bootstrap class loader.
     */
    native public static ClassLoader getCallingClassLoader();

    /**
     * Returns the defining class loader of the caller's caller's caller.
     * 
     * @return the requested class loader, or {@code null} if this is the
     *         bootstrap class loader.
     */
    native public static ClassLoader getCallingClassLoader2();

    /**
     * Returns the class of the caller's caller's caller.
     *
     * @hide
     * @return the requested class, or {@code null}.
     */
    native public static Class<?> getStackClass2();

    /**
     * Creates an array of classes from the methods at the top of the stack.
     * We continue until we reach the bottom of the stack or exceed the
     * specified maximum depth.  If stopAtPrivileged is set, the last
     * element of the array will be the caller of the most-recent privileged
     * method.
     * <p>
     * The topmost stack frame (this method) and the one above that (the
     * caller) are excluded from the array.  Frames with java.lang.reflect
     * classes are skipped over.
     * <p>
     * The classes in the array are the defining classes of the methods.
     * <p>
     * This is expected to be identical to Harmony's VMStack.getClasses.
     *
     * @param maxDepth
     *      maximum number of classes to return, or -1 for all
     * @param stopAtPrivileged
     *      stop when a privileged frame is reached
     * @return an array with classes for the most-recent methods on the stack
     */
    native public static Class<?>[] getClasses(int maxDepth,
        boolean stopAtPrivileged);

    /**
     * Retrieves the stack trace from the specified thread.
     *
     * @param t
     *      thread of interest
     * @return an array of stack trace elements, or null if the thread
     *      doesn't have a stack trace (e.g. because it exited)
     */
    native public static StackTraceElement[] getThreadStackTrace(Thread t);
}

