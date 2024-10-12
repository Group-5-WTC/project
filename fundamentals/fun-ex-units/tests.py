from units import convert

def test_three_units():
    ratios = {
        ("gleep", "glorp"): 3, # 3 gleeps = 1 glorp
        ("shneep", "glorp"): 60, # 60 shneeps = 1 glorp
    }

    # 2 gleeps = 40 shneeps
    assert convert(ratios, "gleep", "shneep", 2) == 40

def test_impossible():
    ratios = {
        ("gleep", "glorp"): 3, # 3 gleeps = 1 glorp
        ("glarg", "toriver"): 70, # 70 glargs = 1 toriver
    }

    # It's impossible to convert gleeps to torivers
    assert convert(ratios, "gleep", "toriver", 1) == None

def test_trivial():
    ratios = {
        ("gleep", "glorp"): 3, # 3 gleeps = 1 glorp
    }

    # 6 gleeps = 2 glorps
    assert convert(ratios, "gleep", "glorp", 6) == 2

def test_trivial_backwards():
    ratios = {
        ("gleep", "glorp"): 3, # 3 gleeps = 1 glorp
    }

    # 2 glorps = 6 gleeps
    assert convert(ratios, "glorp", "gleep", 2) == 6

def test_many_steps():
    ratios = {
        ("millimeter", "centimeter"): 10, # 10 millimeters = 1 centimeter
        ("centimeter", "meter"): 100, # 100 centimeters = 1 meter
        ("meter", "decameter"): 10, # 10 meters = 1 decameter
        ("decameter", "hectometer"): 10, # 10 decameters = 1 hectometer
        ("hectometer", "kilometer"): 10, # 10 hectometers = 1 kilometer
    }

    # 1 kilometer = 1000000 millimeters
    assert convert(ratios, "kilometer", "millimeter", 1) == 1000000