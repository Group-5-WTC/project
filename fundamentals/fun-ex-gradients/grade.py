import sys
import os
import subprocess

path_to_submission = os.path.normpath(sys.argv[1]) + "/"


def run_student_submission(colors, path_to_output):
    colors_strings = [
        ",".join(map(str, coord)) + "=" + ",".join(map(str, color))
        for coord, color in colors.items()
    ]
    process = subprocess.Popen(
        ["python3", "gradient.py",
            *colors_strings,
            "-o", path_to_output,
         ],
        cwd=path_to_submission,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    process.wait()

    stdout = process.stdout.read().decode("utf-8")
    stderr = process.stderr.read().decode("utf-8")

    return stdout, stderr, process.returncode


colors = {
    (10, 10): (255, 0, 10),
    (90, 90): (10, 255, 0),
}

stdout, stderr, returncode = run_student_submission(
    colors,
    "output.bmp"
)

if returncode != 0:
    print("Your program crashed with the following error:")
    print(stderr)
    exit(1)

path_to_output = None

if os.path.exists("output.bmp"):
    path_to_output = "output.bmp"
elif os.path.exists(os.path.join(path_to_submission, "output.bmp")):
    path_to_output = os.path.join(path_to_submission, "output.bmp")
else:
    print("I couldn't find the output file. Make sure you're saving the image to the correct location.")
    exit(1)

with open(path_to_output, "rb") as f:
    output = f.read()

if len(output) == 0:
    print("The output file is empty. Make sure you're writing the image correctly.")
    exit(1)

# check for valid BMP header

if output[:2] != b"BM":
    print("The output file doesn't start with the BMP header. Make sure you're writing a valid BMP file.")
    exit(1)

# get the width and height from the BMP header

width = int.from_bytes(output[18:22], "little")
height = int.from_bytes(output[22:26], "little")

if width != 100 or height != 100:
    print("The output image is not a valid 100x100 bitmap image. Make sure you're setting the width and height correctly.")
    exit(1)

# get the starting offset of the pixel data

offset = int.from_bytes(output[10:14], "little")

# get the pixels at the control points

# pixels = {
#     (10, 10): tuple(output[offset + 10 * 100 * 3 + 10 * 3:offset + 10 * 100 * 3 + 10 * 3 + 3]),
#     (90, 90): tuple(output[offset + 90 * 100 * 3 + 90 * 3:offset + 90 * 100 * 3 + 90 * 3 + 3]),
# }

# # check if the pixels are correct

# if pixels != colors:
#     print("The output image doesn't have the correct colors at the control points. Make sure you're setting the colors correctly.")
#     exit(1)


print("I've tested the basics and it looks like they're working. Great job!")
exit(0)
