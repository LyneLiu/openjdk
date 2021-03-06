/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/**
 * @test TestDynMinHeapFreeRatio
 * @bug 8028391
 * @summary Verify that MinHeapFreeRatio flag is manageable
 * @library /testlibrary
 * @run main TestDynMinHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=0 -XX:MaxHeapFreeRatio=100 TestDynMinHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=50 -XX:-UseAdaptiveSizePolicy TestDynMinHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=50 TestDynMinHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=51 -XX:MaxHeapFreeRatio=52 TestDynMinHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=75 -XX:MaxHeapFreeRatio=100 TestDynMinHeapFreeRatio
 */
import com.oracle.java.testlibrary.TestDynamicVMOption;
import com.oracle.java.testlibrary.DynamicVMOptionChecker;

public class TestDynMinHeapFreeRatio extends TestDynamicVMOption {

    public static final String MinFreeRatioFlagName = "MinHeapFreeRatio";
    public static final String MaxFreeRatioFlagName = "MaxHeapFreeRatio";

    public TestDynMinHeapFreeRatio() {
        super(MinFreeRatioFlagName);
    }

    public void test() {
        int maxHeapFreeValue = DynamicVMOptionChecker.getIntValue(MaxFreeRatioFlagName);
        System.out.println(MaxFreeRatioFlagName + " = " + maxHeapFreeValue);

        testPercentageValues();

        checkInvalidValue(Integer.toString(maxHeapFreeValue + 1));
        checkValidValue(Integer.toString(maxHeapFreeValue));
        checkValidValue("0");
    }

    public static void main(String args[]) throws Exception {
        new TestDynMinHeapFreeRatio().test();
    }
}
