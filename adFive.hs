import Data.List

searchRows :: String -> Int -> Int -> Char -> Char -> Int
searchRows (s:[]) lo hi ch cl   | s == cl = lo
                                | s == ch = hi
searchRows (s:ss) lo hi ch cl   | s == cl = searchRows ss lo ((hi+lo) `div` 2) ch cl
                                | s == ch = searchRows ss ((hi+lo) `div` 2 + 1) hi ch cl

readAndPrint :: FilePath -> IO()
readAndPrint fileP = do
            f <- readFile fileP
            print $ maximum $ [(searchRows (take 7 l) 0 127 'B' 'F' * 8) + searchRows (drop 7 l) 0 7 'R' 'L' | l <- lines f]

findEmpty :: [Int] -> Int
findEmpty (i:is) | head is - i > 1 = i+1
                 | otherwise = findEmpty is 


secondTask :: FilePath -> IO()
secondTask fileP = do
    f <- readFile fileP
    let seats = sort [(searchRows (take 7 l) 0 127 'B' 'F' * 8) + searchRows (drop 7 l) 0 7 'R' 'L' | l <- lines f]
    print $ findEmpty seats 
 


testList l  = (searchRows (take 7 l) 0 127 'B' 'F' * 8) + searchRows (drop 7 l) 0 7 'R' 'L' 