/*
    Copyright (C) 2009  Paul Richards.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package fractals;

final class Triplex
{
    public final double x;
    public final double y;
    public final double z;

    Triplex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    Triplex squashNaNs()
    {
        return new Triplex(
            Utilities.squashNaN(x),
            Utilities.squashNaN(y),
            Utilities.squashNaN(z));
    }

    static Triplex add(Triplex a, Triplex b)
    {
        return new Triplex(
                a.x + b.x,
                a.y + b.y,
                a.z + b.z);
    }
}