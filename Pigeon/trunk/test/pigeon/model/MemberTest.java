/*
    Copyright (C) 2005, 2006, 2007  Paul Richards.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package pigeon.model;

import junit.framework.*;

/**
 *
 * @author Paul
 */
public final class MemberTest extends TestCase {

    public MemberTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MemberTest.class);

        return suite;
    }

    public void testEquality() throws ValidationException {
        Member foo = new Member();
        foo.setName("Foo");
        Member foo2 = new Member();
        foo2.setName("Foo");
        Member bar = new Member();
        bar.setName("Bar");

        assertEquals(foo, foo);
        assertEquals(foo, foo2);
        assertFalse(foo.equals(bar));
    }

    public void testExceptions() {
        try {
            Member foo = new Member();
            foo.setName("");
            assertTrue("Should throw", false);
        } catch (ValidationException ex) {
            assertEquals("Member name is empty", ex.toString());
        }
    }
}
