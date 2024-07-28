module Romberg

export ROMBERG

#a0~b0まで積分する

#RomFunction(io::IO, Fx)=
a0 = 1.0*10^-20;
b0 = 1.0;
len=20;

function ROMBERG(x)
    x
end

function H(i0, a0, b0)
    H_r = (b0 - a0) / 2 ^ (i0 - 1);
end

function Romberg(a0, b0, len::Int64)
    mat = [];
    mat_r = [ 0.0 + 0.0im for a in 1 : len, b in 1 : len];
    mat_r[1,1] = H(1.0, a0, b0) * (ROMBERG(a0) + ROMBERG(b0)) / 2.0;
    i0 = 2;
    while true
        hi = H(i0, a0, b0);
        fx = 0;
        for k = 1 : 2 ^ (i0 - 2)
            x = a0 + (2k - 1)hi;
            fx = fx + ROMBERG(x);
        end
        fx = fx * H(i0-1.0 ,a0 ,b0);
        mat_r[i0,1] = ( mat_r[i0-1,1] + fx ) / 2;

        for j = 2 : i0
            mat_r[i0,j] = mat_r[i0,j-1] + ( mat_r[i0,j-1] - mat_r[i0-1,j-1] ) / ( 4^( j - 1) - 1 );
        end
        if abs(mat_r[i0,i0]) - abs(mat_r[i0-1,i0-1]) < min( abs(mat_r[i0,i0]), abs(mat_r[i0,i0]) )*10.0^( -10 )
            break
        end
        if i0 >= len
            break 
        end
        i0 += 1
    end
    mat = mat_r[i0,i0];
end

return mat

end # module
