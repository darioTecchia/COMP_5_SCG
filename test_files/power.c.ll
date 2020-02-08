; ModuleID = 'test_files/power.c'
source_filename = "test_files/power.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@b = global i32 0, align 4
@a = global i32 0, align 4
@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@result = common local_unnamed_addr global i32 0, align 4
@.str.1 = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.2 = private unnamed_addr constant [12 x i8] c"risultato: \00", align 1

; Function Attrs: nounwind uwtable
define i32 @main() local_unnamed_addr #0 {
  %1 = tail call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* nonnull @a)
  %2 = tail call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* nonnull @b)
  %3 = load i32, i32* @a, align 4, !tbaa !2
  %4 = load i32, i32* @b, align 4, !tbaa !2
  %5 = icmp sgt i32 %4, 0
  br i1 %5, label %6, label %77

; <label>:6:                                      ; preds = %0
  %7 = icmp ult i32 %4, 8
  br i1 %7, label %68, label %8

; <label>:8:                                      ; preds = %6
  %9 = and i32 %4, -8
  %10 = insertelement <4 x i32> undef, i32 %3, i32 0
  %11 = shufflevector <4 x i32> %10, <4 x i32> undef, <4 x i32> zeroinitializer
  %12 = insertelement <4 x i32> undef, i32 %3, i32 0
  %13 = shufflevector <4 x i32> %12, <4 x i32> undef, <4 x i32> zeroinitializer
  %14 = add i32 %9, -8
  %15 = lshr exact i32 %14, 3
  %16 = add nuw nsw i32 %15, 1
  %17 = and i32 %16, 7
  %18 = icmp ult i32 %14, 56
  br i1 %18, label %43, label %19

; <label>:19:                                     ; preds = %8
  %20 = sub nsw i32 %16, %17
  br label %21

; <label>:21:                                     ; preds = %21, %19
  %22 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %19 ], [ %39, %21 ]
  %23 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %19 ], [ %40, %21 ]
  %24 = phi i32 [ %20, %19 ], [ %41, %21 ]
  %25 = mul nsw <4 x i32> %22, %11
  %26 = mul nsw <4 x i32> %23, %13
  %27 = mul nsw <4 x i32> %25, %11
  %28 = mul nsw <4 x i32> %26, %13
  %29 = mul nsw <4 x i32> %27, %11
  %30 = mul nsw <4 x i32> %28, %13
  %31 = mul nsw <4 x i32> %29, %11
  %32 = mul nsw <4 x i32> %30, %13
  %33 = mul nsw <4 x i32> %31, %11
  %34 = mul nsw <4 x i32> %32, %13
  %35 = mul nsw <4 x i32> %33, %11
  %36 = mul nsw <4 x i32> %34, %13
  %37 = mul nsw <4 x i32> %35, %11
  %38 = mul nsw <4 x i32> %36, %13
  %39 = mul nsw <4 x i32> %37, %11
  %40 = mul nsw <4 x i32> %38, %13
  %41 = add i32 %24, -8
  %42 = icmp eq i32 %41, 0
  br i1 %42, label %43, label %21, !llvm.loop !6

; <label>:43:                                     ; preds = %21, %8
  %44 = phi <4 x i32> [ undef, %8 ], [ %39, %21 ]
  %45 = phi <4 x i32> [ undef, %8 ], [ %40, %21 ]
  %46 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %8 ], [ %39, %21 ]
  %47 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %8 ], [ %40, %21 ]
  %48 = icmp eq i32 %17, 0
  br i1 %48, label %58, label %49

; <label>:49:                                     ; preds = %43
  br label %50

; <label>:50:                                     ; preds = %50, %49
  %51 = phi <4 x i32> [ %46, %49 ], [ %54, %50 ]
  %52 = phi <4 x i32> [ %47, %49 ], [ %55, %50 ]
  %53 = phi i32 [ %17, %49 ], [ %56, %50 ]
  %54 = mul nsw <4 x i32> %51, %11
  %55 = mul nsw <4 x i32> %52, %13
  %56 = add i32 %53, -1
  %57 = icmp eq i32 %56, 0
  br i1 %57, label %58, label %50, !llvm.loop !8

; <label>:58:                                     ; preds = %50, %43
  %59 = phi <4 x i32> [ %44, %43 ], [ %54, %50 ]
  %60 = phi <4 x i32> [ %45, %43 ], [ %55, %50 ]
  %61 = mul <4 x i32> %60, %59
  %62 = shufflevector <4 x i32> %61, <4 x i32> undef, <4 x i32> <i32 2, i32 3, i32 undef, i32 undef>
  %63 = mul <4 x i32> %61, %62
  %64 = shufflevector <4 x i32> %63, <4 x i32> undef, <4 x i32> <i32 1, i32 undef, i32 undef, i32 undef>
  %65 = mul <4 x i32> %63, %64
  %66 = extractelement <4 x i32> %65, i32 0
  %67 = icmp eq i32 %4, %9
  br i1 %67, label %77, label %68

; <label>:68:                                     ; preds = %58, %6
  %69 = phi i32 [ 0, %6 ], [ %9, %58 ]
  %70 = phi i32 [ 1, %6 ], [ %66, %58 ]
  br label %71

; <label>:71:                                     ; preds = %68, %71
  %72 = phi i32 [ %75, %71 ], [ %69, %68 ]
  %73 = phi i32 [ %74, %71 ], [ %70, %68 ]
  %74 = mul nsw i32 %73, %3
  %75 = add nuw nsw i32 %72, 1
  %76 = icmp eq i32 %75, %4
  br i1 %76, label %77, label %71, !llvm.loop !10

; <label>:77:                                     ; preds = %71, %58, %0
  %78 = phi i32 [ 1, %0 ], [ %66, %58 ], [ %74, %71 ]
  store i32 %78, i32* @result, align 4, !tbaa !2
  %79 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* getelementptr inbounds ([12 x i8], [12 x i8]* @.str.2, i64 0, i64 0))
  %80 = load i32, i32* @result, align 4, !tbaa !2
  %81 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32 %80)
  ret i32 0
}

; Function Attrs: nounwind
declare i32 @__isoc99_scanf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: norecurse nounwind readnone uwtable
define i32 @power(i32, i32) local_unnamed_addr #2 {
  %3 = icmp sgt i32 %1, 0
  br i1 %3, label %4, label %75

; <label>:4:                                      ; preds = %2
  %5 = icmp ult i32 %1, 8
  br i1 %5, label %66, label %6

; <label>:6:                                      ; preds = %4
  %7 = and i32 %1, -8
  %8 = insertelement <4 x i32> undef, i32 %0, i32 0
  %9 = shufflevector <4 x i32> %8, <4 x i32> undef, <4 x i32> zeroinitializer
  %10 = insertelement <4 x i32> undef, i32 %0, i32 0
  %11 = shufflevector <4 x i32> %10, <4 x i32> undef, <4 x i32> zeroinitializer
  %12 = add i32 %7, -8
  %13 = lshr exact i32 %12, 3
  %14 = add nuw nsw i32 %13, 1
  %15 = and i32 %14, 7
  %16 = icmp ult i32 %12, 56
  br i1 %16, label %41, label %17

; <label>:17:                                     ; preds = %6
  %18 = sub nsw i32 %14, %15
  br label %19

; <label>:19:                                     ; preds = %19, %17
  %20 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %17 ], [ %37, %19 ]
  %21 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %17 ], [ %38, %19 ]
  %22 = phi i32 [ %18, %17 ], [ %39, %19 ]
  %23 = mul nsw <4 x i32> %20, %9
  %24 = mul nsw <4 x i32> %21, %11
  %25 = mul nsw <4 x i32> %23, %9
  %26 = mul nsw <4 x i32> %24, %11
  %27 = mul nsw <4 x i32> %25, %9
  %28 = mul nsw <4 x i32> %26, %11
  %29 = mul nsw <4 x i32> %27, %9
  %30 = mul nsw <4 x i32> %28, %11
  %31 = mul nsw <4 x i32> %29, %9
  %32 = mul nsw <4 x i32> %30, %11
  %33 = mul nsw <4 x i32> %31, %9
  %34 = mul nsw <4 x i32> %32, %11
  %35 = mul nsw <4 x i32> %33, %9
  %36 = mul nsw <4 x i32> %34, %11
  %37 = mul nsw <4 x i32> %35, %9
  %38 = mul nsw <4 x i32> %36, %11
  %39 = add i32 %22, -8
  %40 = icmp eq i32 %39, 0
  br i1 %40, label %41, label %19, !llvm.loop !12

; <label>:41:                                     ; preds = %19, %6
  %42 = phi <4 x i32> [ undef, %6 ], [ %37, %19 ]
  %43 = phi <4 x i32> [ undef, %6 ], [ %38, %19 ]
  %44 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %6 ], [ %37, %19 ]
  %45 = phi <4 x i32> [ <i32 1, i32 1, i32 1, i32 1>, %6 ], [ %38, %19 ]
  %46 = icmp eq i32 %15, 0
  br i1 %46, label %56, label %47

; <label>:47:                                     ; preds = %41
  br label %48

; <label>:48:                                     ; preds = %48, %47
  %49 = phi <4 x i32> [ %44, %47 ], [ %52, %48 ]
  %50 = phi <4 x i32> [ %45, %47 ], [ %53, %48 ]
  %51 = phi i32 [ %15, %47 ], [ %54, %48 ]
  %52 = mul nsw <4 x i32> %49, %9
  %53 = mul nsw <4 x i32> %50, %11
  %54 = add i32 %51, -1
  %55 = icmp eq i32 %54, 0
  br i1 %55, label %56, label %48, !llvm.loop !13

; <label>:56:                                     ; preds = %48, %41
  %57 = phi <4 x i32> [ %42, %41 ], [ %52, %48 ]
  %58 = phi <4 x i32> [ %43, %41 ], [ %53, %48 ]
  %59 = mul <4 x i32> %58, %57
  %60 = shufflevector <4 x i32> %59, <4 x i32> undef, <4 x i32> <i32 2, i32 3, i32 undef, i32 undef>
  %61 = mul <4 x i32> %59, %60
  %62 = shufflevector <4 x i32> %61, <4 x i32> undef, <4 x i32> <i32 1, i32 undef, i32 undef, i32 undef>
  %63 = mul <4 x i32> %61, %62
  %64 = extractelement <4 x i32> %63, i32 0
  %65 = icmp eq i32 %7, %1
  br i1 %65, label %75, label %66

; <label>:66:                                     ; preds = %56, %4
  %67 = phi i32 [ 0, %4 ], [ %7, %56 ]
  %68 = phi i32 [ 1, %4 ], [ %64, %56 ]
  br label %69

; <label>:69:                                     ; preds = %66, %69
  %70 = phi i32 [ %73, %69 ], [ %67, %66 ]
  %71 = phi i32 [ %72, %69 ], [ %68, %66 ]
  %72 = mul nsw i32 %71, %0
  %73 = add nuw nsw i32 %70, 1
  %74 = icmp eq i32 %73, %1
  br i1 %74, label %75, label %69, !llvm.loop !14

; <label>:75:                                     ; preds = %69, %56, %2
  %76 = phi i32 [ 1, %2 ], [ %64, %56 ], [ %72, %69 ]
  ret i32 %76
}

; Function Attrs: nounwind
declare i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

attributes #0 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { norecurse nounwind readnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 6.0.0-1ubuntu2 (tags/RELEASE_600/final)"}
!2 = !{!3, !3, i64 0}
!3 = !{!"int", !4, i64 0}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.isvectorized", i32 1}
!8 = distinct !{!8, !9}
!9 = !{!"llvm.loop.unroll.disable"}
!10 = distinct !{!10, !11, !7}
!11 = !{!"llvm.loop.unroll.runtime.disable"}
!12 = distinct !{!12, !7}
!13 = distinct !{!13, !9}
!14 = distinct !{!14, !11, !7}
