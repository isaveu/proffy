/*
    Copyright (C) 2008  Paul Richards.

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
#include <windows.h>

#include <iostream>
#include <cstdlib>

#pragma warning(disable: 4127) // conditional expression is constant

int main(void)
{
    std::cout << "Running dummy target.\n";
    ::SetPriorityClass(::GetCurrentProcess(), BELOW_NORMAL_PRIORITY_CLASS);
    while(true) {
        for (int i = 50; i <= 100; i++) {
            void* p = malloc(i);
            free(p);
            std::cout << ".";
            std::cout.flush();
            //::Sleep(0);
        }
    }
    return EXIT_SUCCESS;
}